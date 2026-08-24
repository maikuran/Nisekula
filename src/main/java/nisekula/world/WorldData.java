package nisekula.world;

import java.util.HashMap;
import java.util.Map;

public class WorldData {

    // --- ワールドの基本設定・メタデータ ---
    public String worldName;
    public long seed;
    public int width;
    public int height;

    // --- 2Dブロックデータの保持 (X座標とY座標で管理) ---
    // キーとして "x,y" の文字列、あるいはチャンクごとの二次元配列を持たせる形が一般的です
    private final Map<String, CoordinateData> blockMap = new HashMap<>();

    // --- エンティティ管理 ---
    private final Map<Long, EntityData> entities = new HashMap<>();

    public WorldData(String worldName, long seed, int width, int height) {
        this.worldName = worldName;
        this.seed = seed;
        this.width = width;
        this.height = height;
    }

    /**
     * 指定座標のブロックデータを取得する（なければ新規作成）
     */
    public CoordinateData getCoordinate(int x, int y) {
        String key = x + "," + y;
        return blockMap.computeIfAbsent(key, k -> new CoordinateData(x, y));
    }

    /**
     * 指定座標のブロックIDを直接取得
     */
    public int getBlockId(int x, int y) {
        CoordinateData data = blockMap.get(x + "," + y);
        return data != null ? data.blockId : 0; // デフォルトは空気(0)
    }

    /**
     * 指定座標にブロックを設置する
     */
    public void setBlock(int x, int y, int blockId) {
        CoordinateData data = getCoordinate(x, y);
        data.setBlock(blockId);
    }

    /**
     * エンティティの追加
     */
    public void addEntity(EntityData entity) {
        if (entity != null) {
            entities.put(entity.entityId, entity);
        }
    }

    /**
     * エンティティの削除
     */
    public void removeEntity(long entityId) {
        entities.remove(entityId);
    }

    /**
     * 毎フレームのワールド更新処理
     */
    public void tick() {
        // すべてのエンティティの更新
        for (EntityData entity : entities.values()) {
            entity.tick();
        }
    }
}
