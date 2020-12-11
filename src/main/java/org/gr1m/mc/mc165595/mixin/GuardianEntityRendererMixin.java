package org.gr1m.mc.mc165595.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.GuardianEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.GuardianEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GuardianEntityRenderer.class)
public abstract class GuardianEntityRendererMixin
{
	@ModifyVariable(method = "render", index = 10, at = @At(value = "STORE", ordinal = 0, opcode = Opcodes.FSTORE),
					slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getTime()J", ordinal = 0),
								   to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/GuardianEntity;getStandingEyeHeight()F", ordinal = 0)))
	float ModifyK(float k, GuardianEntity guardianEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i)
	{
		return g * 0.5F;
	}

	@ModifyVariable(method = "render", name = "q", index = 19, at = @At(value = "STORE", ordinal = 0, opcode = Opcodes.FSTORE),
					slice = @Slice(from = @At(value = "INVOKE", target="Lnet/minecraft/client/util/math/MatrixStack;multiply(Lnet/minecraft/util/math/Quaternion;)V", ordinal = 1),
								   to = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;cos(F)F", ordinal = 0)))
	float ModifyQ(float q, GuardianEntity guardianEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i)
	{
		return (guardianEntity.world.getTime() * -0.075F) + (g * -0.075F);
	}
}
