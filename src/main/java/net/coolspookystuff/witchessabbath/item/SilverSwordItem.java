package net.coolspookystuff.witchessabbath.item;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SilverSwordItem extends SwordItem {;
private final float attackSpeed;
private final float attackDamage;
public SilverSwordItem(ToolMaterial material, int damage, float speed) {
	super(material, damage, speed, new Item.Settings().group(WitchesSabbath.WITCHES_SABBATH).maxCount(1));
	this.attackSpeed = speed;
	this.attackDamage = damage;
	}
}
