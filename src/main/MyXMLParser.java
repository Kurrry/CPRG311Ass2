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
        if(tag.charAt(1) != '/'){
            String tempTag = tag.replaceAll("[<>]", "");
            stack.push(tag.substring(1, tag.indexOf(' ')));
        }
    }

    private void checkEndTag(String tag) {
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
                    if(stack.get(i).equals(tempTag)) {
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
}
