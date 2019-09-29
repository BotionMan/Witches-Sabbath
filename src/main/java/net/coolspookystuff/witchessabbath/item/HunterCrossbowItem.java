package net.coolspookystuff.witchessabbath.item;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;


import com.google.common.collect.Lists;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criterions;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FireworkEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.Projectile;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HunterCrossbowItem extends RangedWeaponItem {
   private boolean charged = false;
   private boolean loaded = false;

   public HunterCrossbowItem() {
	   super(new Item.Settings().group(WitchesSabbath.WITCHES_SABBATH).maxCount(1));
	   this.addPropertyGetter(new Identifier("pull"), (itemStack_1, world_1, livingEntity_1) -> {
		   if (livingEntity_1 != null && itemStack_1.getItem() == this) {
			   return isCharged(itemStack_1) ? 0.0F : (float)(itemStack_1.getMaxUseTime() - livingEntity_1.getItemUseTimeLeft()) / (float)getPullTime(itemStack_1);
		   } else {
			   return 0.0F;
		   }
	   });
	   this.addPropertyGetter(new Identifier("pulling"), (itemStack_1, world_1, livingEntity_1) -> {
		   return livingEntity_1 != null && livingEntity_1.isUsingItem() && livingEntity_1.getActiveItem() == itemStack_1 && !isCharged(itemStack_1) ? 1.0F : 0.0F;
	   });
	   this.addPropertyGetter(new Identifier("charged"), (itemStack_1, world_1, livingEntity_1) -> {
		   return livingEntity_1 != null && isCharged(itemStack_1) ? 1.0F : 0.0F;
	   });
	   this.addPropertyGetter(new Identifier("firework"), (itemStack_1, world_1, livingEntity_1) -> {
		   return livingEntity_1 != null && isCharged(itemStack_1) && hasProjectile(itemStack_1, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
	   });
   }



public Predicate<ItemStack> getHeldProjectiles() {
      return CROSSBOW_HELD_PROJECTILES;
   }

   public Predicate<ItemStack> getProjectiles() {
      return BOW_PROJECTILES;
   }

   public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
      ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
      if (isCharged(itemStack_1)) {
         shootAll(world_1, playerEntity_1, hand_1, itemStack_1, getSpeed(itemStack_1), 1.0F);
         setCharged(itemStack_1, false);
         return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemStack_1);
      } else if (!playerEntity_1.getArrowType(itemStack_1).isEmpty()) {
         if (!isCharged(itemStack_1)) {
            this.charged = false;
            this.loaded = false;
            playerEntity_1.setCurrentHand(hand_1);
         }

         return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, itemStack_1);
      } else {
         return new TypedActionResult<ItemStack>(ActionResult.FAIL, itemStack_1);
      }
   }

   public void onStoppedUsing(ItemStack itemStack_1, World world_1, LivingEntity livingEntity_1, int int_1) {
      int int_2 = this.getMaxUseTime(itemStack_1) - int_1;
      float float_1 = getPullProgress(int_2, itemStack_1);
      if (float_1 >= 1.0F && !isCharged(itemStack_1) && loadProjectiles(livingEntity_1, itemStack_1)) {
         setCharged(itemStack_1, true);
         SoundCategory soundCategory_1 = livingEntity_1 instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
         world_1.playSound((PlayerEntity)null, livingEntity_1.x, livingEntity_1.y, livingEntity_1.z, SoundEvents.ITEM_CROSSBOW_LOADING_END, soundCategory_1, 1.0F, 1.0F / (RANDOM.nextFloat() * 0.5F + 1.0F) + 0.2F);
      }

   }

   private static boolean loadProjectiles(LivingEntity livingEntity_1, ItemStack itemStack_1) {
      int int_1 = EnchantmentHelper.getLevel(Enchantments.MULTISHOT, itemStack_1);
      int int_2 = int_1 == 0 ? 1 : 3;
      boolean boolean_1 = livingEntity_1 instanceof PlayerEntity && ((PlayerEntity)livingEntity_1).abilities.creativeMode;
      ItemStack itemStack_2 = livingEntity_1.getArrowType(itemStack_1);
      ItemStack itemStack_3 = itemStack_2.copy();

      for(int int_3 = 0; int_3 < int_2; ++int_3) {
         if (int_3 > 0) {
            itemStack_2 = itemStack_3.copy();
         }

         if (itemStack_2.isEmpty() && boolean_1) {
            itemStack_2 = new ItemStack(Items.ARROW);
            itemStack_3 = itemStack_2.copy();
         }

         if (!loadProjectile(livingEntity_1, itemStack_1, itemStack_2, int_3 > 0, boolean_1)) {
            return false;
         }
      }

      return true;
   }

   private static boolean loadProjectile(LivingEntity livingEntity_1, ItemStack itemStack_1, ItemStack itemStack_2, boolean boolean_1, boolean boolean_2) {
      if (itemStack_2.isEmpty()) {
         return false;
      } else {
         boolean boolean_3 = boolean_2 && itemStack_2.getItem() instanceof ArrowItem;
         ItemStack itemStack_4;
         if (!boolean_3 && !boolean_2 && !boolean_1) {
            itemStack_4 = itemStack_2.split(1);
            if (itemStack_2.isEmpty() && livingEntity_1 instanceof PlayerEntity) {
               ((PlayerEntity)livingEntity_1).inventory.removeOne(itemStack_2);
            }
         } else {
            itemStack_4 = itemStack_2.copy();
         }

         putProjectile(itemStack_1, itemStack_4);
         return true;
      }
   }

   public static boolean isCharged(ItemStack itemStack_1) {
      CompoundTag compoundTag_1 = itemStack_1.getTag();
      return compoundTag_1 != null && compoundTag_1.getBoolean("Charged");
   }

   public static void setCharged(ItemStack itemStack_1, boolean boolean_1) {
      CompoundTag compoundTag_1 = itemStack_1.getOrCreateTag();
      compoundTag_1.putBoolean("Charged", boolean_1);
   }

   private static void putProjectile(ItemStack itemStack_1, ItemStack itemStack_2) {
      CompoundTag compoundTag_1 = itemStack_1.getOrCreateTag();
      ListTag listTag_2;
      if (compoundTag_1.containsKey("ChargedProjectiles", 9)) {
         listTag_2 = compoundTag_1.getList("ChargedProjectiles", 10);
      } else {
         listTag_2 = new ListTag();
      }

      CompoundTag compoundTag_2 = new CompoundTag();
      itemStack_2.toTag(compoundTag_2);
      listTag_2.add(compoundTag_2);
      compoundTag_1.put("ChargedProjectiles", listTag_2);
   }

   private static List<ItemStack> getProjectiles(ItemStack itemStack_1) {
      List<ItemStack> list_1 = Lists.newArrayList();
      CompoundTag compoundTag_1 = itemStack_1.getTag();
      if (compoundTag_1 != null && compoundTag_1.containsKey("ChargedProjectiles", 9)) {
         ListTag listTag_1 = compoundTag_1.getList("ChargedProjectiles", 10);
         if (listTag_1 != null) {
            for(int int_1 = 0; int_1 < listTag_1.size(); ++int_1) {
               CompoundTag compoundTag_2 = listTag_1.getCompoundTag(int_1);
               list_1.add(ItemStack.fromTag(compoundTag_2));
            }
         }
      }

      return list_1;
   }

   private static void clearProjectiles(ItemStack itemStack_1) {
      CompoundTag compoundTag_1 = itemStack_1.getTag();
      if (compoundTag_1 != null) {
         ListTag listTag_1 = compoundTag_1.getList("ChargedProjectiles", 9);
         listTag_1.clear();
         compoundTag_1.put("ChargedProjectiles", listTag_1);
      }

   }

   private static boolean hasProjectile(ItemStack itemStack_1, Item item_1) {
      return getProjectiles(itemStack_1).stream().anyMatch((itemStack_1x) -> {
         return itemStack_1x.getItem() == item_1;
      });
   }

   private static void shoot(World world_1, LivingEntity livingEntity_1, Hand hand_1, ItemStack itemStack_1, ItemStack itemStack_2, float float_1, boolean boolean_1, float float_2, float float_3, float float_4) {
      if (!world_1.isClient) {
         boolean boolean_2 = itemStack_2.getItem() == Items.FIREWORK_ROCKET;
         Object projectile_2;
         if (boolean_2) {
            projectile_2 = new FireworkEntity(world_1, itemStack_2, livingEntity_1.x, livingEntity_1.y + (double)livingEntity_1.getStandingEyeHeight() - 0.15000000596046448D, livingEntity_1.z, true);
         } else {
            projectile_2 = createArrow(world_1, livingEntity_1, itemStack_1, itemStack_2);
            if (boolean_1 || float_4 != 0.0F) {
               ((ProjectileEntity)projectile_2).pickupType = ProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
         }

         if (livingEntity_1 instanceof CrossbowUser) {
            CrossbowUser crossbowUser_1 = (CrossbowUser)livingEntity_1;
            crossbowUser_1.shoot(crossbowUser_1.getTarget(), itemStack_1, (Projectile)projectile_2, float_4);
         } else {
            Vec3d vec3d_1 = livingEntity_1.getOppositeRotationVector(1.0F);
            Quaternion quaternion_1 = new Quaternion(new Vector3f(vec3d_1), float_4, true);
            Vec3d vec3d_2 = livingEntity_1.getRotationVec(1.0F);
            Vector3f vector3f_1 = new Vector3f(vec3d_2);
            vector3f_1.method_19262(quaternion_1);
            ((Projectile)projectile_2).setVelocity((double)vector3f_1.getX(), (double)vector3f_1.getY(), (double)vector3f_1.getZ(), float_2, float_3);
         }

         itemStack_1.damage(boolean_2 ? 3 : 1, livingEntity_1, (livingEntity_1x) -> {
            livingEntity_1x.sendToolBreakStatus(hand_1);
         });
         world_1.spawnEntity((Entity)projectile_2);
         world_1.playSound((PlayerEntity)null, livingEntity_1.x, livingEntity_1.y, livingEntity_1.z, SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, float_1);
      }
   }

   private static ProjectileEntity createArrow(World world_1, LivingEntity livingEntity_1, ItemStack itemStack_1, ItemStack itemStack_2) {
      ArrowItem arrowItem_1 = (ArrowItem)((ArrowItem)(itemStack_2.getItem() instanceof ArrowItem ? itemStack_2.getItem() : Items.ARROW));
      ProjectileEntity projectileEntity_1 = arrowItem_1.createArrow(world_1, itemStack_2, livingEntity_1);
      if (livingEntity_1 instanceof PlayerEntity) {
         projectileEntity_1.setDamage(10);
      }

      projectileEntity_1.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
      projectileEntity_1.setShotFromCrossbow(true);
      int int_1 = EnchantmentHelper.getLevel(Enchantments.PIERCING, itemStack_1);
      if (int_1 > 0) {
         projectileEntity_1.setPierceLevel((byte)int_1);
      }

      return projectileEntity_1;
   }

   public static void shootAll(World world_1, LivingEntity livingEntity_1, Hand hand_1, ItemStack itemStack_1, float float_1, float float_2) {
      List<ItemStack> list_1 = getProjectiles(itemStack_1);
      float[] floats_1 = getSoundPitches(livingEntity_1.getRand());

      for(int int_1 = 0; int_1 < list_1.size(); ++int_1) {
         ItemStack itemStack_2 = (ItemStack)list_1.get(int_1);
         boolean boolean_1 = livingEntity_1 instanceof PlayerEntity && ((PlayerEntity)livingEntity_1).abilities.creativeMode;
         if (!itemStack_2.isEmpty()) {
            if (int_1 == 0) {
               shoot(world_1, livingEntity_1, hand_1, itemStack_1, itemStack_2, floats_1[int_1], boolean_1, float_1, float_2, 0.0F);
            } else if (int_1 == 1) {
               shoot(world_1, livingEntity_1, hand_1, itemStack_1, itemStack_2, floats_1[int_1], boolean_1, float_1, float_2, -10.0F);
            } else if (int_1 == 2) {
               shoot(world_1, livingEntity_1, hand_1, itemStack_1, itemStack_2, floats_1[int_1], boolean_1, float_1, float_2, 10.0F);
            }
         }
      }

      postShoot(world_1, livingEntity_1, itemStack_1);
   }

   private static float[] getSoundPitches(Random random_1) {
      boolean boolean_1 = random_1.nextBoolean();
      return new float[]{1.0F, getSoundPitch(boolean_1), getSoundPitch(!boolean_1)};
   }

   private static float getSoundPitch(boolean boolean_1) {
      float float_1 = boolean_1 ? 0.63F : 0.43F;
      return 1.0F / (RANDOM.nextFloat() * 0.5F + 1.8F) + float_1;
   }

   private static void postShoot(World world_1, LivingEntity livingEntity_1, ItemStack itemStack_1) {
      if (livingEntity_1 instanceof ServerPlayerEntity) {
         ServerPlayerEntity serverPlayerEntity_1 = (ServerPlayerEntity)livingEntity_1;
         if (!world_1.isClient) {
            Criterions.SHOT_CROSSBOW.trigger(serverPlayerEntity_1, itemStack_1);
         }

         serverPlayerEntity_1.incrementStat(Stats.USED.getOrCreateStat(itemStack_1.getItem()));
      }

      clearProjectiles(itemStack_1);
   }

   public void usageTick(World world_1, LivingEntity livingEntity_1, ItemStack itemStack_1, int int_1) {
      if (!world_1.isClient) {
         int int_2 = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, itemStack_1);
         SoundEvent soundEvent_1 = this.getQuickChargeSound(int_2);
         SoundEvent soundEvent_2 = int_2 == 0 ? SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE : null;
         float float_1 = (float)(itemStack_1.getMaxUseTime() - int_1) / (float)getPullTime(itemStack_1);
         if (float_1 < 0.2F) {
            this.charged = false;
            this.loaded = false;
         }

         if (float_1 >= 0.2F && !this.charged) {
            this.charged = true;
            world_1.playSound((PlayerEntity)null, livingEntity_1.x, livingEntity_1.y, livingEntity_1.z, soundEvent_1, SoundCategory.PLAYERS, 0.5F, 1.0F);
         }

         if (float_1 >= 0.5F && soundEvent_2 != null && !this.loaded) {
            this.loaded = true;
            world_1.playSound((PlayerEntity)null, livingEntity_1.x, livingEntity_1.y, livingEntity_1.z, soundEvent_2, SoundCategory.PLAYERS, 0.5F, 1.0F);
         }
      }

   }

   public int getMaxUseTime(ItemStack itemStack_1) {
      return getPullTime(itemStack_1) + 3;
   }

   public static int getPullTime(ItemStack itemStack_1) {
      int int_1 = EnchantmentHelper.getLevel(Enchantments.QUICK_CHARGE, itemStack_1);
      return int_1 == 0 ? 25 : 25 - 5 * int_1;
   }

   public UseAction getUseAction(ItemStack itemStack_1) {
      return UseAction.CROSSBOW;
   }

   private SoundEvent getQuickChargeSound(int int_1) {
      switch(int_1) {
      case 1:
         return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_1;
      case 2:
         return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_2;
      case 3:
         return SoundEvents.ITEM_CROSSBOW_QUICK_CHARGE_3;
      default:
         return SoundEvents.ITEM_CROSSBOW_LOADING_START;
      }
   }

   private static float getPullProgress(int int_1, ItemStack itemStack_1) {
      float float_1 = (float)int_1 / (float)getPullTime(itemStack_1);
      if (float_1 > 1.0F) {
         float_1 = 1.0F;
      }

      return float_1;
   }

   @Environment(EnvType.CLIENT)
   public void appendTooltip(ItemStack itemStack_1, World world_1, List<Text> list_1, TooltipContext tooltipContext_1) {
      List<ItemStack> list_2 = getProjectiles(itemStack_1);
      if (isCharged(itemStack_1) && !list_2.isEmpty()) {
         ItemStack itemStack_2 = (ItemStack)list_2.get(0);
         list_1.add((new TranslatableText("item.minecraft.crossbow.projectile", new Object[0])).append(" ").append(itemStack_2.toHoverableText()));
         if (tooltipContext_1.isAdvanced() && itemStack_2.getItem() == Items.FIREWORK_ROCKET) {
            List<Text> list_3 = Lists.newArrayList();
            Items.FIREWORK_ROCKET.appendTooltip(itemStack_2, world_1, list_3, tooltipContext_1);
            if (!list_3.isEmpty()) {
               for(int int_1 = 0; int_1 < list_3.size(); ++int_1) {
                  list_3.set(int_1, (new LiteralText("  ")).append((Text)list_3.get(int_1)).formatted(Formatting.GRAY));
               }

               list_1.addAll(list_3);
            }
         }

      }
   }

   private static float getSpeed(ItemStack itemStack_1) {
      return itemStack_1.getItem() == Items.CROSSBOW && hasProjectile(itemStack_1, Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
   }
}
