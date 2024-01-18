package com.example.fluid;

import com.example.ExampleMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class EndWaterFluid extends AwesomeFluid {
    @Override
    public Fluid getStill() {
        return ExampleMod.STILL_END_WATER;
    }

    @Override
    public Fluid getFlowing() {
        return ExampleMod.FLOWING_END_WATER;
    }

    @Override
    public Item getBucketItem() {
        return ExampleMod.END_WATER_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ExampleMod.END_WATER.getDefaultState()
                .with(FluidBlock.LEVEL, EndWaterFluid.getBlockStateLevel(state));
    }

    public static class Flowing extends EndWaterFluid {

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }
    }

    public static class Still extends EndWaterFluid {
        @Override
        public boolean isStill(FluidState state) {
            return true;
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }
    }
}
