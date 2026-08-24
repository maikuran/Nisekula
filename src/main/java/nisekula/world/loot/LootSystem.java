package nisekula.world.loot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LootSystem {

    private static final Random RANDOM = new Random();

    // ドロップエントリー情報
    public static class LootEntry {
        public final int itemId;
        public final float dropChance; // ドロップ確率 (0.0f ~ 1.0f)
        public final int minCount;
        public final int maxCount;

        public LootEntry(int itemId, float dropChance, int minCount, int maxCount) {
            this.itemId = itemId;
            this.dropChance = dropChance;
            this.minCount = minCount;
            this.maxCount = maxCount;
        }

        public LootEntry(int itemId, float dropChance) {
            this(itemId, dropChance, 1, 1);
        }
    }

    // テーブルマップ
    private static final Map<Integer, List<LootEntry>> ENTITY_LOOT_TABLES = new HashMap<>();
    private static final Map<Integer, List<LootEntry>> BLOCK_LOOT_TABLES = new HashMap<>();

    /**
     * エンティティドロップテーブルの登録
     */
    public static void registerEntityLoot(int entityId, List<LootEntry> entries) {
        ENTITY_LOOT_TABLES.put(entityId, entries);
    }

    /**
     * ブロックドロップテーブルの登録
     */
    public static void registerBlockLoot(int blockId, List<LootEntry> entries) {
        BLOCK_LOOT_TABLES.put(blockId, entries);
    }

    /**
     * モブ死亡時にドロップするアイテム群を算出
     * @param entityId 死亡したモブのID
     * @return ドロップするアイテムIDのリスト (スタック概念がないため個数分アイテムIDが格納される)
     */
    public static List<Integer> EntityLoot(int entityId) {
        List<Integer> drops = new ArrayList<>();
        List<LootEntry> entries = ENTITY_LOOT_TABLES.get(entityId);

        if (entries == null || entries.isEmpty()) {
            return drops;
        }

        for (LootEntry entry : entries) {
            if (RANDOM.nextFloat() <= entry.dropChance) {
                int count = entry.minCount + (entry.maxCount > entry.minCount ? RANDOM.nextInt(entry.maxCount - entry.minCount + 1) : 0);
                for (int i = 0; i < count; i++) {
                    drops.add(entry.itemId);
                }
            }
        }
        return drops;
    }

    /**
     * ブロック破壊時にドロップするアイテム群を算出
     * @param blockId 破壊されたブロックのID
     * @return ドロップするアイテムIDのリスト
     */
    public static List<Integer> BlockLoot(int blockId) {
        List<Integer> drops = new ArrayList<>();
        List<LootEntry> entries = BLOCK_LOOT_TABLES.get(blockId);

        if (entries == null || entries.isEmpty()) {
            return drops;
        }

        for (LootEntry entry : entries) {
            if (RANDOM.nextFloat() <= entry.dropChance) {
                int count = entry.minCount + (entry.maxCount > entry.minCount ? RANDOM.nextInt(entry.maxCount - entry.minCount + 1) : 0);
                for (int i = 0; i < count; i++) {
                    drops.add(entry.itemId);
                }
            }
        }
        return drops;
    }
}
