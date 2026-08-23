package nisekula.world.item;

public class InventoryLogic {

    // --- インベントリ容量定義 ---
    public static final int MAX_STORAGE_SIZE = 896; // メイン所持枠 (スタックなし)
    public static final int ACCESSORY_SLOTS = 3;    // アクセサリー枠数

    // --- メイン所持枠 (アイテムIDの配列 / 0 = 空白) ---
    private final int[] storage = new int[MAX_STORAGE_SIZE];

    // --- 装備スロット ---
    public int offHandItem = 0;       // 左手
    public int helmet = 0;            // 兜
    public int armor = 0;             // 鎧
    public final int[] accessories = new int[ACCESSORY_SLOTS]; // アクセサリー3個

    // --- メインインベントリ操作 ---

    /**
     * アイテムを1つ拾う / 追加する (スタックなし)
     * @return 追加に成功したら true、満杯なら false
     */
    public boolean addItem(int itemId) {
        if (itemId == 0) return false;
        
        for (int i = 0; i < MAX_STORAGE_SIZE; i++) {
            if (storage[i] == 0) {
                storage[i] = itemId;
                return true;
            }
        }
        return false; // インベントリが満杯 (896個埋まっている)
    }

    /**
     * 指定スロットのアイテムを取り出す / 捨てる
     * @return 取り出されたアイテムID (空なら0)
     */
    public int removeItem(int slotIndex) {
        if (slotIndex < 0 || slotIndex >= MAX_STORAGE_SIZE) return 0;
        int item = storage[slotIndex];
        storage[slotIndex] = 0;
        return item;
    }

    /** 指定スロットのアイテムIDを取得 */
    public int getItem(int slotIndex) {
        if (slotIndex < 0 || slotIndex >= MAX_STORAGE_SIZE) return 0;
        return storage[slotIndex];
    }

    // --- 装備スロット操作 ---

    /** 左手のアイテムを入れ替える */
    public int swapOffHand(int storageSlot) {
        if (storageSlot < 0 || storageSlot >= MAX_STORAGE_SIZE) return 0;
        int oldOffHand = this.offHandItem;
        this.offHandItem = storage[storageSlot];
        storage[storageSlot] = oldOffHand;
        return this.offHandItem;
    }

    /** 兜を入れ替える */
    public int swapHelmet(int storageSlot) {
        if (storageSlot < 0 || storageSlot >= MAX_STORAGE_SIZE) return 0;
        int oldHelmet = this.helmet;
        this.helmet = storage[storageSlot];
        storage[storageSlot] = oldHelmet;
        return this.helmet;
    }

    /** 鎧を入れ替える */
    public int swapArmor(int storageSlot) {
        if (storageSlot < 0 || storageSlot >= MAX_STORAGE_SIZE) return 0;
        int oldArmor = this.armor;
        this.armor = storage[storageSlot];
        storage[storageSlot] = oldArmor;
        return this.armor;
    }

    /** アクセサリーを入れ替える (accSlotIndex: 0~2) */
    public int swapAccessory(int accSlotIndex, int storageSlot) {
        if (accSlotIndex < 0 || accSlotIndex >= ACCESSORY_SLOTS) return 0;
        if (storageSlot < 0 || storageSlot >= MAX_STORAGE_SIZE) return 0;

        int oldAcc = accessories[accSlotIndex];
        accessories[accSlotIndex] = storage[storageSlot];
        storage[storageSlot] = oldAcc;
        return accessories[accSlotIndex];
    }

    /** 空きスロットの数を取得 */
    public int getFreeSpace() {
        int count = 0;
        for (int id : storage) {
            if (id == 0) count++;
        }
        return count;
    }

    /** 全インベントリの配列参照（描画・GUI用） */
    public int[] getStorage() {
        return storage;
    }
}
