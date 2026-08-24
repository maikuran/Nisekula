package nisekula.world.feature;

import nisekula.lib.regi.FeatureRegistry;
import nisekula.world.WorldData;
import nisekula.world.block.Blocks;

import java.util.Random;

public class OreFeature {

    // --- 鉱石ブロックIDの定義（仮にBlocks側で定義されているものとします） ---
    // もし別のクラス名やIDであれば適宜書き換えてください
    public static final int YELLOW_CRYSTAL_ORE = Blocks.YELLOW_CRYSTAL_ORE;
    public static final int DIAMOND_ORE        = Blocks.DIAMOND_ORE;
    public static final int IRON_ORE           = Blocks.IRON_ORE;

    private static final Random RANDOM = new Random();

    /**
     * 鉱石の生成機能を一括初期化・登録する
     */
    public static void init() {
        // --- 1. イエロークリスタル鉱石 (ややレア、中層〜深層) ---
        FeatureRegistry.r(201, "yellow_crystal_ore",
            new FeatureRegistry.Properties().ch(0.04f).height(10, 50).size(6),
            (world, x, y) -> generateOreVein(world, x, y, YELLOW_CRYSTAL_ORE, 1, 3, 7)
        );

        // --- 2. ダイヤ鉱石 (超レア、深層のみ) ---
        FeatureRegistry.r(202, "diamond_ore",
            new FeatureRegistry.Properties().ch(0.015f).height(1, 20).size(5),
            (world, x, y) -> generateOreVein(world, x, y, DIAMOND_ORE, 1, 3, 6)
        );

        // --- 3. 鉄鉱石 (比較的よく出る、全層〜中層) ---
        FeatureRegistry.r(203, "iron_ore",
            new FeatureRegistry.Properties().ch(0.08f).height(10, 80).size(9),
            (world, x, y) -> generateOreVein(world, x, y, IRON_ORE, 1, 3, 9)
        );
    }

    /**
     * 指定された鉱石を指定のルール（1〜3個ずつ広がり、最大9個など）で塊として生成・書き換える共通処理
     * @param world ワールドデータ (WorldData)
     * @param startX 生成開始X座標
     * @param startY 生成開始Y座標
     * @param oreBlockId 埋め込む鉱石のブロックID
     * @param minCluster 1回あたりに配置する最小ブロック数 (1〜3)
     * @param maxCluster 1回あたりに配置する最大ブロック数 (1〜3)
     * @param maxTotalSize 鉱脈全体の最大ブロック数（例: 最大9個など）
     */
    private static void generateOreVein(Object world, int startX, int startY, int oreBlockId, int minCluster, int maxCluster, int maxTotalSize) {
        if (!(world instanceof WorldData)) return;
        WorldData worldData = (WorldData) world;

        int placedCount = 0;
        int currentX = startX;
        int currentY = startY;

        // 最大サイズに達するか、これ以上広げられなくなるまでループ
        while (placedCount < maxTotalSize) {
            // 今回の塊のサイズを 1〜3 の間でランダム決定
            int clusterSize = minCluster + RANDOM.nextInt(maxCluster - minCluster + 1);

            for (int i = 0; i < clusterSize; i++) {
                // 現在位置のブロックが石（あるいは地中ブロック）である場合のみ置き換える
                int currentBlock = worldData.getBlockId(currentX, currentY);
                if (currentBlock == Blocks.STONE || currentBlock == Blocks.DEEPSLATE) {
                    worldData.setBlock(currentX, currentY, oreBlockId);
                    placedCount++;
                }

                // 次のブロックへランダムに隣接移動（2Dなので上下左右に散らす）
                int dir = RANDOM.nextInt(4);
                switch (dir) {
                    case 0: currentX++; break;
                    case 1: currentX--; break;
                    case 2: currentY++; break;
                    case 3: currentY--; break;
                }

                if (placedCount >= maxTotalSize) break;
            }

            // 塊同士が少し離れるようにランダムで少し位置をジャンプさせる
            if (RANDOM.nextFloat() < 0.3f) {
                currentX = startX + RANDOM.nextInt(5) - 2;
                currentY = startY + RANDOM.nextInt(5) - 2;
            }
        }
    }
}
