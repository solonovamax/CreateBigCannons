package rbasamoyai.createbigcannons.munitions.big_cannon;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.jozufozu.flywheel.backend.instancing.blockentity.BlockEntityInstance;
import com.jozufozu.flywheel.core.Materials;
import com.jozufozu.flywheel.core.materials.oriented.OrientedData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import rbasamoyai.createbigcannons.index.CBCBlockPartials;

public class FuzedBlockInstance extends BlockEntityInstance<FuzedBlockEntity> implements DynamicInstance {

	private final OrientedData fuze;
	private final FuzedBlockEntity shell;
	
	public FuzedBlockInstance(MaterialManager materialManager, FuzedBlockEntity blockEntity) {
		super(materialManager, blockEntity);
		
		this.shell = blockEntity;
		this.fuze = materialManager.defaultCutout()
				.material(Materials.ORIENTED)
				.getModel(CBCBlockPartials.FUZE, this.blockState, this.blockState.getValue(BlockStateProperties.FACING))
				.createInstance();
		this.fuze.setPosition(this.instancePos);
	}

	@Override public BlockPos getWorldPosition() { return this.shell.getBlockPos(); }
	
	@Override
	public void beginFrame() {
		this.fuze.setColor((byte) 255, (byte) 255, (byte) 255, this.shell.hasFuze() ? (byte) 255 : (byte) 0);
	}

	@Override
	public void remove() {
		this.fuze.delete();
	}
	
	@Override
	public void updateLight() {
		super.updateLight();
		this.fuze.updateLight(this.world, this.pos);
	}

}
