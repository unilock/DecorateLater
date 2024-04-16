package cc.unilock.decolater.transformer;

import cc.unilock.decolater.util.BiomeDecoratorStorage;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraft.world.WorldServer")
public class WorldServerTransformer extends MiniTransformer {
	@Patch.Method("tick()V")
	public void patchTick(PatchContext ctx) {
		ctx.jumpToLastReturn();

		ctx.add(
				INVOKESTATIC("cc/unilock/decolater/transformer/WorldServerTransformer$Hooks", "tick", "()V")
		);
	}

	public static class Hooks {
		private static int ticks = 0;

		public static void tick() {
			if (ticks < 20) {
				ticks++;
			} else {
				ticks = 0;

				if (BiomeDecoratorStorage.INSTANCE.isReady()) {
					BiomeDecoratorStorage.INSTANCE.decorate();
				}
			}
		}
	}
}
