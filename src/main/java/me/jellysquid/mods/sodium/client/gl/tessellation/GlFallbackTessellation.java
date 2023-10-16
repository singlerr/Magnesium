package me.jellysquid.mods.sodium.client.gl.tessellation;

import me.jellysquid.mods.sodium.client.gl.attribute.GlVertexAttributeBinding;
import me.jellysquid.mods.sodium.client.gl.device.CommandList;
import me.jellysquid.mods.sodium.compat.lwjgl.CompatGL20C;

public class GlFallbackTessellation extends GlAbstractTessellation {
    public GlFallbackTessellation(GlPrimitiveType primitiveType, TessellationBinding[] bindings) {
        super(primitiveType, bindings);
    }

    @Override
    public void delete(CommandList commandList) {

    }

    @Override
    public void bind(CommandList commandList) {
        this.bindAttributes(commandList);
    }

    @Override
    public void unbind(CommandList commandList) {
        for (TessellationBinding binding : this.bindings) {
            for (GlVertexAttributeBinding attrib : binding.getAttributeBindings()) {
                CompatGL20C.glDisableVertexAttribArray(attrib.getIndex());
            }
        }
    }
}
