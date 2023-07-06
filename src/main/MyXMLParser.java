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
        String tempTag = tag.replaceAll("[<>]", "");
        stack.push(tag.substring(1, tag.indexOf(' ')));
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
                int index = 0;
                boolean matchFound = false;
                while(stack.hasNext()) {
                    String compareString = stack.get(index).substring(0, ' ');
                    if(compareString.equals(tempTag)) {
                        matchFound = true;
                        break;
                    }
                    stack.next();
                    index++;
                }
                if (matchFound) {
                    for (int j = 0; j < index; j++) {
                        errorQ.enqueue(stack.pop());
                    }
                    stack.pop();
                } else extraQ.enqueue(tag);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cleanStack() {
        while (stack.hasNext()) {
            errorQ.enqueue(stack.pop());
        }
        if(stack.size() == 1) errorQ.enqueue(stack.pop());
    }

    private void cleanQueue() {
        boolean errorQEmpty = errorQ.size() == 0;

        if(errorQEmpty) {
            extraQ.dequeueAll();
        } else {
            errorQ.dequeueAll();
        }
    }

    private void cleanBothQueues() {
        try {
            while (errorQ.size() > 0 && extraQ.size() > 0) {
                String errorQTag = errorQ.peek().replaceAll("[<>/]", "");
                String extraQTag = extraQ.peek().replaceAll("[<>/]", "");
                boolean compareTag = errorQTag.equals(extraQTag);

                if (compareTag) {
                    errorQ.dequeue();
                    extraQ.dequeue();
                } else {
                    System.out.println(errorQ.dequeue());
                }
            }

            if (errorQ.size() > 0) {
                errorQ.dequeueAll();
            }

            if (extraQ.size() > 0) {
                extraQ.dequeueAll();
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
