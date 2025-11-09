public class Trie implements Constants {
    private Node root;

    public Trie() {
        root = new Node();
    }

    // Преобразование символа в индекс массива
    private int toID(char c) throws ArrayIndexOutOfBoundsException {
        int id = c - FIRST_CHAR;
        if (id < 0 || id > LETTERS - 1) {
            throw new ArrayIndexOutOfBoundsException(ERROR_INVALID_SYMBOL + ": '" + c + "'");
        }
        return id;
    }

    // Преобразование индекса обратно в символ
    private char toChar(int i) {
        return (char)(FIRST_CHAR + i);
    }

    // Поиск узла по строке (возвращает узел последнего символа или null)
    private Node findNode(String word) throws IllegalArgumentException {
        if (word == null) {
            throw new IllegalArgumentException(ERROR_NULL_PTR);
        }

        Node cur = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int id = toID(c);

            if (cur.children[id] == null) return null;
            cur = cur.children[id];
        }
        return cur;
    }

    // Подсчёт количества слов в поддереве
    private int countWords(Node cur) {
        if (cur == null) return 0;
        int count = cur.isEnd ? 1 : 0;

        for (int i = 0; i < LETTERS; ++i) {
            if (cur.children[i] != null) {
                count += countWords(cur.children[i]);
            }
        }
        return count;
    }

    // Рекурсивный сбор всех слов из поддерева
    private int findWords(Node cur, String word, String[] words, int id) {
        if (cur.isEnd) words[id++] = word;

        for (int i = 0; i < LETTERS; ++i) {
            if (cur.children[i] != null) {
                char c = toChar(i);
                id = findWords(cur.children[i], word + c, words, id);
            }
        }
        return id;
    }

    // Рекурсивное сравнение структур двух поддеревьев
    private boolean equalsNodes(Node node1, Node node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (node1.isEnd != node2.isEnd) return false;

        for (int i = 0; i < LETTERS; ++i) {
            if (!equalsNodes(node1.children[i], node2.children[i])) {
                return false;
            }
        }
        return true;
    }

    // Вставка слова в дерево
    public void insert(String word) throws IllegalArgumentException {
        if (word == null) {
            throw new IllegalArgumentException(ERROR_NULL_PTR);
        }

        word = word.toLowerCase();
        Node cur = root;

        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int id = toID(c);

            if (cur.children[id] == null) {
                cur.children[id] = new Node();
            }
            cur = cur.children[id];
        }
        cur.isEnd = true;
    }

    // Проверка наличия слова в дереве
    public boolean contains(String word) throws IllegalArgumentException {
        word = word.toLowerCase();
        Node node = findNode(word);
        return node != null && node.isEnd;
    }

    // Проверка существования слов с данным префиксом
    public boolean startsWith(String prefix) throws IllegalArgumentException {
        prefix = prefix.toLowerCase();
        Node cur = findNode(prefix);
        if (cur == null) return false;

        for (int i = 0; i < LETTERS; ++i) {
            if (cur.children[i] != null) return true;
        }
        return false;
    }

    // Получение всех слов с заданным префиксом
    public String[] getByPrefix(String prefix) throws IllegalArgumentException {
        prefix = prefix.toLowerCase();
        Node cur = findNode(prefix);
        if (cur == null) return new String[0];

        int size = countWords(cur);
        String[] words = new String[size];
        findWords(cur, prefix, words, 0);
        return words;
    }

    @Override
    public int hashCode() {
        int hash = HASH_BASE;

        for (int i = 0; i < LETTERS; ++i) {
            if (root.children[i] != null) {
                char ch = toChar(i);
                String prefix = String.valueOf(ch);

                String[] wordsOnPref = getByPrefix(prefix);
                    for (int j = 0; j < wordsOnPref.length; ++j) {
                        if (wordsOnPref[j] != null) {
                            int strHash = wordsOnPref[j].hashCode();
                            hash = ((hash * HASH_MULT) % HASH_MOD + strHash) % HASH_MOD;
                        }
                    }
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < LETTERS; ++i) {
            if (root.children[i] != null) {
                char ch = toChar(i);
                String prefix = String.valueOf(ch);
                String[] words = getByPrefix(prefix);
                if (words.length > 0) {
                    result.append(ch).append(" : ");
                    for (int j = 0; j < words.length - 1; ++j) {
                        result.append(words[j]).append(", ");
                    }
                    result.append(words[words.length - 1]);
                    result.append('\n');
                }
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Trie)) return false;

        Trie other = (Trie) obj;
        return equalsNodes(this.root, other.root);
    }

    // Проверка на пустоту дерева
    public boolean isEmpty() {
        return countWords(root) == 0;
    }

    // Очистка дерева через создание нового корня
    public void clear() {
        root = new Node();
    }
}