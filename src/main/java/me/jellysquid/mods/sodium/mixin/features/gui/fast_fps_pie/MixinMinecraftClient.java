package me.jellysquid.mods.sodium.mixin.features.gui.fast_fps_pie;

import me.jellysquid.mods.sodium.compat.client.renderer.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.profiler.ProfileResult;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.text.DecimalFormat;

@Mixin(Minecraft.class)
public class MixinMinecraftClient {
    @Shadow
    @Final
    public FontRenderer fontRenderer;

    //private VertexConsumerProvider.Immediate immediate;

    @Inject(method = "displayDebugInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I", ordinal = 0))
    private void preRenderText(long p_71366_1_, CallbackInfo ci) {
        //this.immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
    }

    @Redirect(method = "displayDebugInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
    private int drawWithShadow(FontRenderer instance, String text, float x, float y, int color) {
        if (text != null) {
            return this.fontRenderer.drawString(text, x, y, color, true);
        }
        return 0;
    }

    @Inject(method = "displayDebugInfo", at = @At("TAIL"))
    private void renderText(long p_71366_1_, CallbackInfo ci) {
        //this.immediate.draw();
    }
}