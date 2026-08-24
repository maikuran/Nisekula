package nisekula.world.item;

import java.util.HashMap;
import java.util.Map;

public class ItemDurability {

    // --- 1. 耐久値定義（道具系 / 鎧系 / 兜系） ---
    // ティア: [1:木, 2:石, 3:鉄, 4:イエロークリスタル, 5:ダイヤ]
    public static final int[] TOOL_DURABILITY  = {42, 101, 550, 1200, 2048};
    public static final int[] ARMOR_DURABILITY = {61, 97, 230, 490, 771};
    public static final int[] HELMET_DURABILITY = {52, 82, 209, 423, 668};

    // --- 2. 耐久値の状態を保持するデータ構造 ---
    public static class DurabilityData {
        public final int maxDurability; // 元々の最大耐久値
        public int currentMax;          // 現在の外側の限界値（使用ごとに削れる）

        public DurabilityData(int maxDurability) {
            this.maxDurability = maxDurability;
            this.currentMax = maxDurability;
        }

        /**
         * 耐久値を外側（最大値）から減らす
         * @param amount 減らす量
         * @return 壊れた場合（耐久値が0以下になった場合）は true
         */
        public boolean consumeFromOuter(int amount) {
            this.currentMax -= amount;
            if (this.currentMax <= 0) {
                this.currentMax = 0;
                return true; // 破損
            }
            return false;
        }

        /** 残り耐久割合 (0.0f ~ 1.0f) */
        public float getRatio() {
            if (maxDurability <= 0) return 0.0f;
            return (float) currentMax / maxDurability;
        }
    }

    // アイテムごとのインスタンス耐久管理 (アイテムのインスタンスIDやスロットインデックス等で管理)
    private static final Map<Long, DurabilityData> DURABILITY_MAP = new HashMap<>();

    /**
     * 新しいアイテム耐久データを生成・登録
     */
    public static DurabilityData createData(long itemInstanceId, int maxDurability) {
        DurabilityData data = new DurabilityData(maxDurability);
        DURABILITY_MAP.put(itemInstanceId, data);
        return data;
    }

    /**
     * 階級(tier)とカテゴリから初期最大耐久値を取得する
     * @param category 1:道具(Wand, Sword, Spear等), 2:鎧, 3:兜
     * @param tier 1~5
     */
    public static int getMaxDurabilityByTier(int category, int tier) {
        int index = Math.max(0, Math.min(tier - 1, 4));
        switch (category) {
            case 1: return TOOL_DURABILITY[index];
            case 2: return ARMOR_DURABILITY[index];
            case 3: return HELMET_DURABILITY[index];
            default: return 100;
        }
    }

    /**
     * 使用時に外側から耐久を1減らすヘルパー関数
     * @return 壊れたら true
     */
    public static boolean useItem(long itemInstanceId) {
        DurabilityData data = DURABILITY_MAP.get(itemInstanceId);
        if (data == null) return false;
        return data.consumeFromOuter(1);
    }

    public static DurabilityData getData(long itemInstanceId) {
        return DURABILITY_MAP.get(itemInstanceId);
    }
}
