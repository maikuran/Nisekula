package nisekula.world.item;

import nisekula.lib.regi.ItemRegistry;
import static nisekula.lib.regi.ItemRegistry.r;
import static nisekula.lib.regi.ItemRegistry.Tag.*;

public class WandItem {

    // --- WandのアイテムID定義 (Alpha1Recipesの仮ID 11~15と完全に一致させる) ---
    public static final int WOODEN_WAND         = 11;
    public static final int STONE_WAND          = 12;
    public static final int IRON_WAND           = 13;
    public static final int YELLOW_CRYSTAL_WAND = 14;
    public static final int DIAMOND_WAND        = 15;

    // Wand専用のプロパティ構造
    public static class WandProperties extends ItemRegistry.Properties {
        public float attackSpeed; // 攻撃速度

        public WandProperties(float damage, float speed, float miningSpeed, int tier) {
            dmg(damage);
            this.attackSpeed = speed;
            speed(miningSpeed); // 採掘速度倍率
            tier(tier);
            tag(WAND, UTILITY, OFFHANDITEM); // 掘削ツール・ユーティリティ・副手装備タグを付与
        }
    }

    /**
     * 5種類のWandを一括登録
     */
    public static void init() {
        // 木のWand: 攻撃力 6.0, 攻撃速度 0.90, 採掘倍率 1.0
        r(WOODEN_WAND, "wooden_wand", 
            new WandProperties(6.0f, 0.90f, 1.0f, 1));

        // 石のWand: 攻撃力 7.0, 攻撃速度 0.89, 採掘倍率 2.0
        r(STONE_WAND, "stone_wand", 
            new WandProperties(7.0f, 0.89f, 2.0f, 2));

        // 鉄のWand: 攻撃力 8.0, 攻撃速度 0.87, 採掘倍率 4.5
        r(IRON_WAND, "iron_wand", 
            new WandProperties(8.0f, 0.87f, 4.5f, 3));

        // イエロークリスタルのWand: 攻撃力 9.0, 攻撃速度 0.85, 採掘倍率 7.5
        r(YELLOW_CRYSTAL_WAND, "yellow_crystal_wand", 
            new WandProperties(9.0f, 0.85f, 7.5f, 4));

        // ダイヤのWand: 攻撃力 10.0, 攻撃速度 0.82, 採掘倍率 15.5
        r(DIAMOND_WAND, "diamond_wand", 
            new WandProperties(10.0f, 0.82f, 15.5f, 5));
    }

    /**
     * 独自の採掘時間計算式
     * 
     * 【計算ロジック】
     * 基礎破壊時間 = ブロックの硬度 (strength) * 1.5
     * 最終破壊時間（秒） = 基礎破壊時間 / ツール採掘倍率 (miningSpeed)
     * (※0秒未満にならないよう最低値を制限)
     *
     * @param blockStrength ブロックの硬度 (例: 石なら1.5、ダイヤ鉱石なら4.0など)
     * @param wandMiningSpeed ツールの採掘倍率 (1.0 〜 15.5)
     * @return 破壊にかかる秒数
     */
    public static float calculateMiningTime(float blockStrength, float wandMiningSpeed) {
        if (blockStrength <= 0.0f) return 0.0f; // 空気や一瞬で壊れるもの
        if (wandMiningSpeed <= 0.0f) wandMiningSpeed = 1.0f;

        float baseTime = blockStrength * 1.5f;
        float finalTime = baseTime / wandMiningSpeed;

        // 最低でも 0.05秒（3フレーム程度）はかかるようにクランプ
        return Math.max(0.05f, finalTime);
    }
}
