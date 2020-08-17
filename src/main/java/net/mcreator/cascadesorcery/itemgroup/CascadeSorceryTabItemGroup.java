
package net.mcreator.cascadesorcery.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.cascadesorcery.item.Shadow_IngotIngotItem;
import net.mcreator.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class CascadeSorceryTabItemGroup extends CascadeSorceryModElements.ModElement {
	public CascadeSorceryTabItemGroup(CascadeSorceryModElements instance) {
		super(instance, 16);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabcascade_sorcery_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Shadow_IngotIngotItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
