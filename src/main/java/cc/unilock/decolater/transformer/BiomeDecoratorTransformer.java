package cc.unilock.decolater.transformer;

import cc.unilock.decolater.DecoLaterPremain;
import cc.unilock.decolater.util.BiomeDecoratorStorage;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import nilloader.api.lib.asm.tree.LabelNode;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

import java.util.Random;

@Patch.Class("net.minecraft.world.biome.BiomeDecorator")
public class BiomeDecoratorTransformer extends MiniTransformer {
	@Patch.Method("decorate(Lnet/minecraft/world/World;Ljava/util/Random;II)V")
	public void patchDecorate(PatchContext ctx) {
		ctx.jumpToStart();

		LabelNode Lcontinue = new LabelNode();

		ctx.add(
				ALOAD(0),
				ALOAD(1),
				ALOAD(2),
				ILOAD(3),
				ILOAD(4),
				INVOKESTATIC("cc/unilock/decolater/transformer/BiomeDecoratorTransformer$Hooks", "decorate", "(Lnet/minecraft/world/biome/BiomeDecorator;Lnet/minecraft/world/World;Ljava/util/Random;II)Z"),
				IFEQ(Lcontinue),
				RETURN(),
				Lcontinue
		);
	}

	public static class Hooks {
		public static boolean decorate(BiomeDecorator decorator, World var1, Random var2, int var3, int var4) {
			if (decorator.currentWorld != null) {
				DecoLaterPremain.LOGGER.warn("Already decorating!!");
				BiomeDecoratorStorage.INSTANCE.add(decorator, var1, var2, var3, var4);

				return true;
			}

			return false;
		}
	}
}
