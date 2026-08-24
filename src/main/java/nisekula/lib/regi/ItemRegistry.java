package nisekula.lib.regi;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.EnumSet;

import nisekula.world.CoordinateData;
import nisekula.world.block.Blocks;

public class ItemRegistry {

    // --- 1. アイテムタグ定義 ---
    public enum Tag {
        BLOCKS,      // ブロックアイテム
        FOOD,        //食べ物
        ELECTROS,    // 電気・回路系
        WAND,        // 万能掘削ツール (突き刺して破壊)
        HOE,         // クワ (耕作)
        SWORD,       // 剣 (斬撃)
        SPEAR,       // 槍 (刺突)
        WORLDGEN,    // ワールド生成関連
        NATURE,      // 自然・植物・素材
        ARMOR,       // 鎧
        HELMET,      // 兜
        ACCESSORY,   // アクセサリー
        OFFHANDITEM, // 左手装備可能
        UTILITY      // ユーティリティ系道具
    }

    // --- 2. アイテムの属性データ ---
    public static class Properties {
        public float attackDamage = 0.0f; // 攻撃力
        public float miningSpeed = 1.0f;  // 採掘速度 multiplier
        public int tier = 0;              // ツール性能階級
        public Set<Tag> tags = EnumSet.noneOf(Tag.class);

        /** タグを追加 */
        public Properties tag(Tag... newTags) {
            for (Tag t : newTags) {
                this.tags.add(t);
            }
            return this;
        }

        public Properties dmg(float dmg) { this.attackDamage = dmg; return this; }
        public Properties speed(float spd) { this.miningSpeed = spd; return this; }
        public Properties tier(int t) { this.tier = t; return this; }

        public boolean hasTag(Tag tag) {
            return tags.contains(tag);
        }
    }

    // --- 3. アイテム定義構造 ---
    public static class ItemType {
        public final int id;
        public final String name;
        public final Properties props;

        public ItemType(int id, String name, Properties props) {
            this.id = id;
            this.name = name;
            this.props = props;
        }

        /**
         * Wand (万能掘削棒) の特殊処理: 突き刺してブロックを破壊する
         * @param coord 対象の座標データ
         * @return 破壊されたブロックのID (破壊不能または空気なら 0)
         */
        public int onWandPierce(CoordinateData coord) {
            if (!props.hasTag(Tag.WAND)) return 0;
            if (coord == null || coord.isAir()) return 0;

            // 空気以外のすべてのブロック（木・土・石等）を突き刺して一律破壊
            return coord.breakBlock();
        }
    }

    private static final Map<Integer, ItemType> ITEMS = new HashMap<>();
    private static final Map<String, Integer> NAME_TO_ID = new HashMap<>();

    // --- 登録関数 (r) ---
    public static ItemType r(int id, String name, Properties props) {
        ItemType type = new ItemType(id, name, props);
        ITEMS.put(id, type);
        NAME_TO_ID.put(name, id);
        return type;
    }

    // --- 取得関数 (g) ---
    public static ItemType g(int id) {
        return ITEMS.get(id);
    }

    public static ItemType g(String name) {
        Integer id = NAME_TO_ID.get(name);
        return id != null ? g(id) : null;
    }
}
