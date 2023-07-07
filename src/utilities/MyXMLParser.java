package utilities;

import datastructures.MyArrayList;
import datastructures.MyQueue;
import datastructures.MyStack;
import exceptions.EmptyQueueException;

import java.io.BufferedReader;
import java.io.FileReader;

public class MyXMLParser {
    MyQueue<String> errorQ;
    MyQueue<String> extraQ;
    MyStack<String> stack;
    String fileName;
    public MyXMLParser(String[] args) {
        errorQ = new MyQueue<>();
        extraQ = new MyQueue<>();
        stack = new MyStack<>();
        fileName = args[0].replaceAll("\"", "");
        parseXMLFile();
    }

    private boolean isSelfClosingTag(String tag) {
        return tag.charAt(tag.length() - 2) == '/';
    }

    private void pushStartTag(String tag) {
        String tempTag = tag.replaceAll("[<>]", "");
        try {
            stack.push(tempTag.substring(0, tag.indexOf(' ')).trim());
        } catch (StringIndexOutOfBoundsException ex) {
            stack.push(tempTag.trim());
        }
    }

    private void checkEndTag(String tag) {
        String tempTag = tag.replaceAll("[<>/]", "");
        String tempStackHead = stack.peek()
                .replaceAll("[<>/]", "");

        try {
            if (tempTag.equals(tempStackHead)){
                stack.pop();
            }
            else if (!errorQ.isEmpty() && tempTag.equals(errorQ.peek()
                    .replaceAll("[<>/]", ""))) {
                errorQ.dequeue();
            }
            else if (stack.isEmpty()) {
                errorQ.enqueue(tempTag.trim());
            }
            else {
                int index = 0;
                boolean matchFound = false;
                while(stack.hasNext()) {
                    String compareString = "";
                    try {
                        compareString = stack.get(index).substring(0, ' ').trim();
                    } catch (StringIndexOutOfBoundsException ex) {
                        compareString = stack.get(index);
                    }
                    if(compareString.equals(tempTag)) {
                        matchFound = true;
                        break;
                    }
                    stack.next();
                    index++;
                }
                if (matchFound) {
                    for (int j = 0; j < index; j++) {
                        errorQ.enqueue(stack.pop().trim());
                    }
                    stack.pop();
                } else extraQ.enqueue(tag.trim());
            }
        } catch (EmptyQueueException ex) {
            ex.printStackTrace();
        }
    }

    private void cleanStack() {
        while (stack.hasNext()) {
            errorQ.enqueue(stack.pop().trim());
        }
    }

    private void cleanQueue() {
        boolean errorQEmpty = errorQ.size() == 0;

        if(errorQEmpty) {
            System.out.println("extraQ purge");
            extraQ.dequeueAll();
        } else {
            System.out.println("errorQ purge");
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
                    System.out.println(errorQ.dequeue() + ": is error");
                }
            }

            if (errorQ.size() > 0) {
                System.out.println("errorQ purge both queues");
                errorQ.dequeueAll();
            }

            if (extraQ.size() > 0) {
                System.out.println("extraQ purge both queues");
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
    private boolean isClosingTag(String tag) {
        return tag.charAt(1) == '/';
    }

    private boolean isProperFormat(String tag) {
        return tag.charAt(0) == '<' && tag.charAt(tag.length() - 1) == '>';
    }

    public void parseXMLFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.trim().replaceAll("\t", "");
                if(!isProperFormat(line)) {
                    System.out.println(line + ": is not in proper XML tag format.");
                    continue;
                }

                if (isSelfClosingTag(line)) {
                    continue;
                }

                if (!isClosingTag(line)) {
                    pushStartTag(line);
                } else {
                    checkEndTag(line);
                }
            }

            if(!stack.isEmpty()) {
                cleanStack();
            }

            if(!extraQ.isEmpty() && !errorQ.isEmpty()) {
                cleanBothQueues();
            }

            if(extraQ.isEmpty() ^ errorQ.isEmpty()) {
                cleanQueue();
            }
        } catch (Exception ex) {
            //System.out.println("Please enter a valid file path.");
            ex.printStackTrace();
            System.exit(1);
        }
    }
}