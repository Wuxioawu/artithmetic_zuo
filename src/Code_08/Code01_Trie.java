package Code_08;

import tools.Constants;
import tools.NumberOperation;

import java.util.HashMap;

public class Code01_Trie {
    // use fixed array to finish
    public static class PrefixTree {
        static class Node {
            public int pass;
            public int end;

            public Node[] nextNodes;

            public Node() {
                this.pass = 0;
                this.end = 0;
                this.nextNodes = new Node[26];
            }
        }

        private final Node root;

        public PrefixTree() {
            root = new Node();
        }

        public void insert(String words) {
            if (words.isEmpty()) return;
            char[] charArray = words.toCharArray();
            Node nodes = root;
            for (char c : charArray) {
                int index = c - 'a';
                nodes.pass++;
                Node nextNode = nodes.nextNodes[index];
                if (nextNode == null) {
                    nextNode = new Node();
                }
                nodes = nextNode;
            }
            nodes.pass++;
            nodes.end++;
        }

        //Return how many times the exact string str exists in the Trie.
        public int search(String words) {
            Node resultNode = findResultNode(words);
            if (resultNode == null) return 0;
            return resultNode.end;
        }

        public void delete(String word) {
            if (word.isEmpty()) return;
            if (search(word) < 1) return;

            char[] charArray = word.toCharArray();
            Node nodes = root;
            for (char c : charArray) {
                int index = c - 'a';
                nodes.pass--;

                if (nodes.pass == 0) {
                    nodes.nextNodes = null;
                }

                assert nodes.nextNodes != null;
                Node nextNode = nodes.nextNodes[index];
                if (nextNode == null) {
                    return;
                }
            }

            nodes.pass--;
            nodes.end--;

            if (nodes.pass == 0) {
                nodes.nextNodes = null;
            }
        }

        public int prefixNumber(String words) {
            Node resultNode = findResultNode(words);
            if (resultNode == null) return 0;
            return resultNode.pass;
        }

        private Node findResultNode(String words) {
            if (words.isEmpty()) return null;
            char[] charArray = words.toCharArray();
            Node nodes = root;
            for (char c : charArray) {
                int index = c - 'a';
                Node nextNode = nodes.nextNodes[index];
                if (nextNode == null) {
                    return null;
                }
                nodes = nextNode;
            }
            return nodes;
        }
    }

    //use hashMao to finish
    public static class PrefixTreeByMap {

        private static class NodeByMap {
            public int pass;
            public int end;

            public HashMap<Character, NodeByMap> nextNodes;

            public NodeByMap() {
                this.pass = 0;
                this.end = 0;
                nextNodes = new HashMap<>();
            }
        }

        private final NodeByMap root;

        public PrefixTreeByMap() {
            root = new NodeByMap();
        }

        public void insert(String words) {
            if (words.isEmpty()) return;
            char[] charArray = words.toCharArray();
            NodeByMap node = root;
            node.pass++;
            for (char c : charArray) {
                if (node.nextNodes.containsKey(c)) {
                    node.nextNodes.get(c).pass++;
                } else {
                    NodeByMap nodeByMap = new NodeByMap();
                    nodeByMap.pass++;
                    node.nextNodes.put(c, nodeByMap);
                }
                node = node.nextNodes.get(c);
            }
            node.end++;
        }

        public int search(String words) {
            NodeByMap resultNode = findResultNode(words);
            if (resultNode == null) return 0;
            return resultNode.end;
        }

        public void delete(String words) {
            if (words.isEmpty()) return;
            if (search(words) < 1) return;

            char[] charArray = words.toCharArray();
            NodeByMap node = root;
            node.pass--;
            for (char c : charArray) {
                node = node.nextNodes.get(c);
                node.pass--;
            }
            node.end--;
        }

        public int prefixNumber(String words) {
            NodeByMap resultNode = findResultNode(words);
            if (resultNode == null) return 0;
            return resultNode.pass;
        }

        private NodeByMap findResultNode(String words) {
            if (words.isEmpty()) return null;
            char[] charArray = words.toCharArray();
            NodeByMap node = root;
            for (char c : charArray) {
                node = node.nextNodes.get(c);
                if (node == null) return null;
            }
            return node;
        }
    }

    public static String[] getRandomStringS(int maxSize) {
        int length = NumberOperation.getRandomNumber(maxSize);

        String[] words = new String[length];

        for (int i = 0; i < length; i++) {
            words[i] = getRandomString(26);
            words[i] = deleteRepeat(words[i]);
        }
        return words;
    }

    public static String getRandomString(int maxSize) {
        int length = NumberOperation.getRandomNumber(maxSize);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = (char) ('a' + NumberOperation.getRandomNumber(26));
            result.append(c);
        }
        return result.toString();
    }

    public static String deleteRepeat(String words) {
        if (words.isEmpty()) return "";
        char[] charArray = words.toCharArray();
        StringBuilder result = new StringBuilder();
        boolean[] isRepeat = new boolean[26];
        for (char c : charArray) {
            if (!isRepeat[c - 'a']) {
                isRepeat[c - 'a'] = true;
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String getPrefix(String words) {
        if (words.isEmpty()) return "";
        int endIndex = NumberOperation.getRandomNumber(words.length());
        return words.substring(0, endIndex);
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            String[] words = getRandomStringS(100);

            PrefixTree prefixTreeByArr = new PrefixTree();
            PrefixTreeByMap prefixTreeByMap = new PrefixTreeByMap();

            for (String word : words) {
                int operation = NumberOperation.getRandomNumber(4);
                switch (operation) {
                    case 0: {
                        prefixTreeByArr.insert(word);
                        prefixTreeByMap.insert(word);
                        break;
                    }
                    case 1: {
                        prefixTreeByArr.delete(word);
                        prefixTreeByMap.delete(word);
                        break;
                    }
                    case 2: {
                        int search = prefixTreeByArr.search(word);
                        int search1 = prefixTreeByMap.search(word);
                        if (search != search1) {
                            System.out.println(Constants.CODE_ERROR);
                            return;
                        }
                        break;
                    }
                    case 3: {
                        String prefix = getPrefix(word);
                        int i1 = prefixTreeByArr.prefixNumber(prefix);
                        int i2 = prefixTreeByMap.prefixNumber(prefix);
                        if (i1 != i2) {
                            System.out.println(Constants.CODE_ERROR);
                            return;
                        }
                        break;
                    }
                }
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }
}
