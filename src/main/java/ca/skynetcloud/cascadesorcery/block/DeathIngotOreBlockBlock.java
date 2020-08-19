
package net.mcreator.cascadesorcery.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.cascadesorcery.itemgroup.CascadeSorceryTabItemGroup;
import net.mcreator.cascadesorcery.CascadeSorceryModElements;

import java.util.List;
import java.util.Collections;

@CascadeSorceryModElements.ModElement.Tag
public class DeathIngotOreBlockBlock extends CascadeSorceryModElements.ModElement {
	@ObjectHolder("cascade_sorcery:death_ingot_ore_block")
	public static final Block block = null;
	public DeathIngotOreBlockBlock(CascadeSorceryModElements instance) {
		super(instance, 22);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(
				() -> new BlockItem(block, new Item.Properties().group(CascadeSorceryTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(5f, 10f).lightValue(0).harvestLevel(7)
					.harvestTool(ToolType.PICKAXE));
			setRegistryName("death_ingot_ore_block");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
