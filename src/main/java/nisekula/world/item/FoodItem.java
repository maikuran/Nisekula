package nisekula.world.item;

import nisekula.lib.regi.ItemRegistry;
import nisekula.world.EntityData;
import static nisekula.lib.regi.ItemRegistry.r;

public class FoodItem {

    // --- 飲食アイテムのID定義 ---
    public static final int CENTIPEDE_MEAT   = 401; // ムカデ肉
    public static final int APPLE            = 402; // リンゴ
    public static final int ORANGE           = 403; // ミカン
    public static final int ORANGE_JUICE     = 404; // ミカンジュース
    public static final int APPLE_JUICE      = 405; // リンゴジュース

    // 食料データの拡張構造
    public static class FoodProperties extends ItemRegistry.Properties {
        public float healAmount; // HP回復量
        public boolean isDrink;  // 飲み物かどうか (ジュース判定用)

        public FoodProperties(float healAmount, boolean isDrink) {
            this.healAmount = healAmount;
            this.isDrink = isDrink;
        }
    }

    /**
     * 食料アイテムを一括登録する処理
     */
    public static void init() {
        // ムカデ肉: 回復量 2.0 (生肉なので控えめ、あるいは毒リスクなどの拡張用)
        r(CENTIPEDE_MEAT, "centipede_meat", new FoodProperties(2.0f, false));

        // リンゴ: 回復量 4.0
        r(APPLE, "apple", new FoodProperties(4.0f, false));

        // ミカン: 回復量 3.0
        r(ORANGE, "orange", new FoodProperties(3.0f, false));

        // ミカンジュース: 回復量 5.0 (素早く回復する飲み物)
        r(ORANGE_JUICE, "orange_juice", new FoodProperties(5.0f, true));

        // リンゴジュース: 回復量 6.0
        r(APPLE_JUICE, "apple_juice", new FoodProperties(6.0f, true));
    }

    /**
     * エンティティが食料を消費してHPを回復するシステム処理
     * @param entity 対象のエンティティ (プレイヤーやモブ)
     * @param itemId 食べたアイテムのID
     * @return 消費・回復に成功したら true
     */
    public static boolean consumeFood(EntityData entity, int itemId) {
        if (entity == null || entity.isDead) return false;

        ItemRegistry.ItemType itemType = ItemRegistry.g(itemId);
        if (itemType == null || !(itemType.props instanceof FoodProperties)) {
            return false; // 食料ではない
        }

        FoodProperties food = (FoodProperties) itemType.props;

        // HP回復処理 (最大HPを超えないように回復)
        float newHp = Math.min(entity.maxHp, entity.hp + food.healAmount);
        if (newHp > entity.hp) {
            entity.hp = newHp;
            return true; // 回復成功
        }

        return false; // すでにHPが満タンの場合など
    }
}
