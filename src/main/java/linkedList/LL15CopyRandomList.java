package linkedList;


/**
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next
 * 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针
 * 都不应指向原链表中的节点 。
 *
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，
 * 同样有 x.random --> y 。
 *
 * 返回复制链表的头节点。
 *
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 *
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 *
 * medium
 */

//TODO 后期看
public class LL15CopyRandomList {

    /**
     * 方法一：回溯 + 哈希表
     * 思路及算法
     *
     * 本题要求我们对一个特殊的链表进行深拷贝。如果是普通链表，我们可以直接按照遍历的顺序创建链表节点。而本题中因为随机指针
     * 的存在，当我们拷贝节点时，「当前节点的随机指针指向的节点」可能还没创建，因此我们需要变换思路。一个可行方案是，我们利
     * 用回溯的方式，让每个节点的拷贝操作相互独立。对于当前节点，我们首先要进行拷贝，然后我们进行「当前节点的后继节点」和
     * 「当前节点的随机指针指向的节点」拷贝，拷贝完成后将创建的新节点的指针返回，即可完成当前节点的两指针的赋值。
     *
     * 具体地，我们用哈希表记录每一个节点对应新节点的创建情况。遍历该链表的过程中，我们检查「当前节点的后继节点」和「当前节
     * 点的随机指针指向的节点」的创建情况。如果这两个节点中的任何一个节点的新节点没有被创建，我们都立刻递归地进行创建。当我
     * 们拷贝完成，回溯到当前层时，我们即可完成当前节点的指针赋值。注意一个节点可能被多个其他节点指向，因此我们可能递归地多
     * 次尝试拷贝某个节点，为了防止重复拷贝，我们需要首先检查当前节点是否被拷贝过，如果已经拷贝过，我们可以直接从哈希表中取
     * 出拷贝后的节点的指针并返回即可。
     *
     * 在实际代码中，我们需要特别判断给定节点为空节点的情况。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 n是链表的长度。对于每个节点，我们至多访问其「后继节点」和「随机指针指向的节点」各一次，均摊每
     * 个点至多被访问两次。
     *
     * 空间复杂度：O(n)，其中 n是链表的长度。为哈希表的空间开销。
     */

/*    Map<ListNodeRandom, ListNodeRandom> cachedListNode = new HashMap<>();

    public ListNodeRandom copyRandomList(ListNodeRandom head) {
        if (head == null) {
            return null;
        }
        if (!cachedListNode.containsKey(head)) {
            ListNodeRandom headNew = new ListNodeRandom(head.val);
            cachedListNode.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cachedListNode.get(head);
    }*/

    /**
     * 方法二：迭代 + 节点拆分
     * 思路及算法
     *
     * 注意到方法一需要使用哈希表记录每一个节点对应新节点的创建情况，而我们可以使用一个小技巧来省去哈希表的空间。
     *
     * 我们首先将该链表中每一个节点拆分为两个相连的节点，例如对于链表A→B→C，我们可以将其拆分为A→A′→B→B′→C→C′。对于
     * 任意一个原节点 S，其拷贝节点 S′即为其后继节点。这样，我们可以直接找到每一个拷贝节点 S′的随机指针应当指向的节点，即
     * 为其原节点 S 的随机指针指向的节点 T 的后继节点 T′。需要注意原节点的随机指针可能为空，我们需要特别判断这种情况。
     *
     * 当我们完成了拷贝节点的随机指针的赋值，我们只需要将这个链表按照原节点与拷贝节点的种类进行拆分即可，只需要遍历一次。
     * 同样需要注意最后一个拷贝节点的后继节点为空，我们需要特别判断这种情况。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 n 是链表的长度。我们只需要遍历该链表三次。
     *
     * 读者们也可以自行尝试在计算拷贝节点的随机指针的同时计算其后继指针，这样只需要遍历两次。
     * 空间复杂度：O(1)。注意返回值不计入空间复杂度。
     */

    public ListNodeRandom copyRandomList(ListNodeRandom head) {
        if (head == null) {
            return null;
        }
        for (ListNodeRandom node = head; node != null; node = node.next.next) {
            ListNodeRandom nodeNew = new ListNodeRandom(node.val);
            nodeNew.next = node.next;
            node.next = nodeNew;
        }
        for (ListNodeRandom node = head; node != null; node = node.next.next) {
            ListNodeRandom nodeNew = node.next;
            nodeNew.random = (node.random != null) ? node.random.next : null;
        }
        ListNodeRandom headNew = head.next;
        for (ListNodeRandom node = head; node != null; node = node.next) {
            ListNodeRandom nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return headNew;
    }

}
