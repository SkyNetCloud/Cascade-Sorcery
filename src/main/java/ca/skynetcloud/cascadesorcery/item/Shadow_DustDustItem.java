
package ca.skynetcloud.cascadesorcery.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import ca.skynetcloud.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class Shadow_DustDustItem extends CascadeSorceryModElements.ModElement {
	@ObjectHolder("cascade_sorcery:shadow_dust_dust")
	public static final Item block = null;
	public Shadow_DustDustItem(CascadeSorceryModElements instance) {
		super(instance, 43);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(64));
			setRegistryName("shadow_dust_dust");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
