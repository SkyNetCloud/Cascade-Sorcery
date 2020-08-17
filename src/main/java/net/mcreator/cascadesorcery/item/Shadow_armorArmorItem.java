
package net.mcreator.cascadesorcery.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.Entity;

import net.mcreator.cascadesorcery.itemgroup.CascadeSorceryTabItemGroup;
import net.mcreator.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class Shadow_armorArmorItem extends CascadeSorceryModElements.ModElement {
	@ObjectHolder("cascade_sorcery:shadow_armor_armor_helmet")
	public static final Item helmet = null;
	@ObjectHolder("cascade_sorcery:shadow_armor_armor_chestplate")
	public static final Item body = null;
	@ObjectHolder("cascade_sorcery:shadow_armor_armor_leggings")
	public static final Item legs = null;
	@ObjectHolder("cascade_sorcery:shadow_armor_armor_boots")
	public static final Item boots = null;
	public Shadow_armorArmorItem(CascadeSorceryModElements instance) {
		super(instance, 5);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 147;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{20, 59, 49, 20}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 88;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "shadow_armor_armor";
			}

			public float getToughness() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(CascadeSorceryTabItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "cascade_sorcery:textures/models/armor/shadow_armor_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("shadow_armor_armor_helmet"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.CHEST, new Item.Properties().group(CascadeSorceryTabItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "cascade_sorcery:textures/models/armor/shadow_armor_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("shadow_armor_armor_chestplate"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.LEGS, new Item.Properties().group(CascadeSorceryTabItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "cascade_sorcery:textures/models/armor/shadow_armor_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("shadow_armor_armor_leggings"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.FEET, new Item.Properties().group(CascadeSorceryTabItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "cascade_sorcery:textures/models/armor/shadow_armor_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("shadow_armor_armor_boots"));
	}
}
