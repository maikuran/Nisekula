package nisekula.lib.regi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityRegistry {

    // --- 1. エンティティの基本ステータス (Properties) ---
    public static class Properties {
        public float maxHp = 20.0f;     // 最大体力
        public float speed = 1.0f;      // 移動速度
        public float width = 0.6f;      // 当たり判定の幅
        public float height = 1.8f;     // 当たり判定の高さ
        public boolean isHostile = false; // 敵対的かどうか
        public float attackDamage = 0f; // 攻撃力

        // ビルダーパターン風に短縮設定できるようにする
        public Properties hp(float hp) { this.maxHp = hp; return this; }
        public Properties spd(float spd) { this.speed = spd; return this; }
        public Properties size(float w, float h) { this.width = w; this.height = h; return this; }
        public Properties hostile(boolean h) { this.isHostile = h; return this; }
        public Properties dmg(float dmg) { this.attackDamage = dmg; return this; }
    }

    // --- 2. 行動AI・目的の設定 (Goals) ---
    public static class Goals {
        private final List<String> goalList = new ArrayList<>();

        /** 行動パターンの追加 (例: "wander", "attack_player", "flee") */
        public Goals add(String goalName) {
            goalList.add(goalName);
            return this;
        }

        public List<String> getList() {
            return goalList;
        }
    }

    // --- 3. エンティティの定義データ ---
    public static class EntityType {
        public final int id;
        public final String name;
        public final Properties props;
        public final Goals goals;

        public EntityType(int id, String name, Properties props, Goals goals) {
            this.id = id;
            this.name = name;
            this.props = props;
            this.goals = goals;
        }
    }

    private static final Map<Integer, EntityType> ENTITIES = new HashMap<>();
    private static final Map<String, Integer> NAME_TO_ID = new HashMap<>();

    // --- 登録関数 (r) ---
    public static EntityType r(int id, String name, Properties props, Goals goals) {
        EntityType type = new EntityType(id, name, props, goals);
        ENTITIES.put(id, type);
        NAME_TO_ID.put(name, id);
        return type;
    }

    // --- 取得関数 (g) ---
    public static EntityType g(int id) {
        return ENTITIES.get(id);
    }

    public static EntityType g(String name) {
        Integer id = NAME_TO_ID.get(name);
        return id != null ? g(id) : null;
    }
}
