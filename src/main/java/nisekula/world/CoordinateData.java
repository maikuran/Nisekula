package nisekula.world;

import nisekula.world.block.Blocks;

public class CoordinateData {

    public int x;
    public int y;
    
    // 配置データ
    public int blockId;      // 前景ブロック (ブロックID)
    public int wallId;       // 背景ブロック (壁ID / マイクラの背景壁)
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
        this.metadata = 0; // 状態リセット
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
        return oldBlock; // 壊れたブロックのIDを返す（ドロップアイテム生成用）
    }

    /** 空気ブロック（何もない状態）かどうか */
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
