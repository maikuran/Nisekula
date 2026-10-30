package nisekula.world;

import nisekula.world.block.Blocks;
import java.util.HashMap;
import java.util.Map;
public class World {
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
