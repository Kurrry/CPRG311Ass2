package main;

import exceptions.EmptyQueueException;

public class MyXMLParser {
    MyQueue<String> errorQ;
    MyQueue<String> extraQ;
    MyStack<String> stack;
    public MyXMLParser() {
        errorQ = new MyQueue<>();
        extraQ = new MyQueue<>();
        stack = new MyStack<>();
    }

    private boolean isSelfClosingTag(String tag) {
        return tag.charAt(tag.length() - 2) == '/';
    }

    private void pushStartTag(String tag) {
        if(checkTagType(tag)) return;
        String tempTag = tag.replaceAll("[<>]", "");
        stack.push(tag.substring(1, tag.indexOf(' ')));
    }

    private void checkEndTag(String tag) {
        if(!checkTagType(tag)) return;
        String tempError = "";
        String tempTag = tag.replaceAll("[<>/]", "");
        String tempStackHead = stack.peek()
                .replaceAll("[<>/]", "");
        try {
             tempError = errorQ.peek()
                    .replaceAll("[<>/]", "");
        } catch (EmptyQueueException ex) {
            ex.printStackTrace();
        }

        try {
            if (tempTag.equals(tempStackHead)) stack.pop();
            else if (!errorQ.isEmpty() && tempTag.equals(tempError)) errorQ.dequeue();
            else if (stack.isEmpty()) errorQ.enqueue(tag);
            else {
                boolean matchFound = false;
                for(int i = 0; i < stack.size() - 1; i++) {
                    String compareString = stack.get(i).substring(0, ' ');
                    if(compareString.equals(tempTag)) {
                        for(int j = 0; j <= i; j++) {
                            errorQ.enqueue(stack.pop());
                        }
                        stack.pop();
                        break;
                    }
                }
                if(!matchFound) extraQ.enqueue(tag);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * return true if the second character in the string is /
     * if true the tag is a closing tag
     * @param tag
     * @return true if second character is /
     */
    private boolean checkTagType(String tag) {
        return tag.charAt(1) == '/';
    }
}
