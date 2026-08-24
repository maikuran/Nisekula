package nisekula.data.loot.entity;

import nisekula.world.entity.LuxCentipedeEntity;
import nisekula.world.item.FoodItem;
import nisekula.world.loot.LootSystem;
import nisekula.world.block.Blocks;

import java.util.Arrays;

public class LuxCentipedeLoot {

    /**
     * 白光ムカデのドロップテーブルを初期化・登録する
     */
    public static void init() {
        LootSystem.registerEntityLoot(LuxCentipedeEntity.ENTITY_ID, Arrays.asList(
            // ムカデ肉 (確率 80%、1〜2個ドロップ)
            new LootSystem.LootEntry(FoodItem.CENTIPEDE_MEAT, 0.80f, 1, 2),

            // 白光石 / 白光系素材ブロック (確率 50%、1個ドロップ)
            new LootSystem.LootEntry(Blocks.LUXALBSTONE, 0.50f, 1, 1)
        ));
    }
}
