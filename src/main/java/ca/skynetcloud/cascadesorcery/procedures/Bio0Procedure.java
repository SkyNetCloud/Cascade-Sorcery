package ca.skynetcloud.cascadesorcery.procedures;

import net.minecraft.world.IWorld;

import java.util.Map;

import ca.skynetcloud.cascadesorcery.CascadeSorceryModVariables;
import ca.skynetcloud.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class Bio0Procedure extends CascadeSorceryModElements.ModElement {
	public Bio0Procedure(CascadeSorceryModElements instance) {
		super(instance, 29);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure Bio0!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		CascadeSorceryModVariables.MapVariables.get(world).BioEnter = (double) 0;
		CascadeSorceryModVariables.MapVariables.get(world).syncData(world);
	}
}
