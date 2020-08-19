package ca.skynetcloud.cascadesorcery.procedures;

import net.minecraft.world.IWorld;

import java.util.Map;

import ca.skynetcloud.cascadesorcery.CascadeSorceryModVariables;
import ca.skynetcloud.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class PowerGenProcedure extends CascadeSorceryModElements.ModElement {
	public PowerGenProcedure(CascadeSorceryModElements instance) {
		super(instance, 18);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PowerGen!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getWorld().isDaytime())
				&& ((CascadeSorceryModVariables.MapVariables.get(world).BioEng) < (CascadeSorceryModVariables.MapVariables.get(world).BioMax)))
				&& ((CascadeSorceryModVariables.MapVariables.get(world).BioEnter) == 1))) {
			CascadeSorceryModVariables.MapVariables
					.get(world).BioEng = (double) ((CascadeSorceryModVariables.MapVariables.get(world).BioEng) + Math.max(20000, 10000));
			CascadeSorceryModVariables.MapVariables.get(world).syncData(world);
			if (((CascadeSorceryModVariables.MapVariables.get(world).BioEng) > (CascadeSorceryModVariables.MapVariables.get(world).BioMax))) {
				CascadeSorceryModVariables.MapVariables.get(world).BioEng = (double) (CascadeSorceryModVariables.MapVariables.get(world).BioMax);
				CascadeSorceryModVariables.MapVariables.get(world).syncData(world);
			}
		}
	}
}
