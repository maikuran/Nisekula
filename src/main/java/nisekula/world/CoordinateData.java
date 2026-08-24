package nisekula.world;

import nisekula.world.block.Blocks;
import java.util.HashMap;
import java.util.Map;

public class CoordinateData {

    public int x;
    public int y;
    
    // 配置データ
    public int blockId;      // 前景ブロック (ブロックID)
    public int wallId;       // 背景ブロック (壁ID)
    public byte lightLevel;  // 明るさ (0 ~ 15)
    public byte metadata;    // ブロックの向きや状態データ

    public CoordinateData(int x, int y) {
        this(x, y, Blocks.AIR, Blocks.AIR);
    }

    public CoordinateData(int x, int y, int blockId, int wallId) {
        this.x = x;
        this.y = y;
        this.blockId = blockId;
        this.wallId = wallId;
        this.lightLevel = 0;
        this.metadata = 0;
    }

    // --- ブロック操作関数 ---

    /** ブロックを設置する */
    public void setBlock(int newBlockId) {
        this.blockId = newBlockId;
        this.metadata = 0;
    }

    /** ブロックを設置する (メタデータ付き) */
    public void setBlock(int newBlockId, byte meta) {
        this.blockId = newBlockId;
        this.metadata = meta;
    }

    /** ブロックを破壊する (空気にする) */
    public int breakBlock() {
        int oldBlock = this.blockId;
        this.blockId = Blocks.AIR;
        this.metadata = 0;
        return oldBlock;
    }

    /** 空気ブロックかどうか */
    public boolean isAir() {
        return this.blockId == Blocks.AIR;
    }

    /** 背景壁を破壊する */
    public int breakWall() {
        int oldWall = this.wallId;
        this.wallId = Blocks.AIR;
        return oldWall;
    }

    /** 座標のクリア */
    public void clear() {
        this.blockId = Blocks.AIR;
        this.wallId = Blocks.AIR;
        this.lightLevel = 0;
        this.metadata = 0;
    }
}

/**
 * 同一ファイル内に定義されたWorldクラス
 * `world.setBlock(x, y, blockId)` を実行するためのコンテナ
 */
class World {
    private final Map<String, CoordinateData> blockMap = new HashMap<>();

    /**
     * 指定座標のブロックデータを取得する（なければ新規作成）
     */
    public CoordinateData getCoordinate(int x, int y) {
        String key = x + "," + y;
        return blockMap.computeIfAbsent(key, k -> new CoordinateData(x, y));
    }

    /**
     * 指定座標にブロックを設置する
     */
    public void setBlock(int x, int y, int blockId) {
        CoordinateData data = getCoordinate(x, y);
        data.setBlock(blockId);
    }

    /**
     * 指定座標のブロックIDを取得する
     */
    public int getBlockId(int x, int y) {
        CoordinateData data = blockMap.get(x + "," + y);
        return data != null ? data.blockId : Blocks.AIR;
    }
}
