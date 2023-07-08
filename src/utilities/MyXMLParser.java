package utilities;

import datastructures.MyQueue;
import datastructures.MyStack;
import exceptions.EmptyQueueException;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * basic implementation of an XML parser. parser prefers tags in a line by line structure.
 * will display any tags that are improperly structured in the supplied file.
 */
public class MyXMLParser {
    MyQueue<String> errorQ;
    MyQueue<String> extraQ;
    MyStack<String> stack;
    String fileName;

    /**
     * MyXMLParser constructor
     * @param args command line arguments containing XML file
     */
    public MyXMLParser(String[] args) {
        errorQ = new MyQueue<>();
        extraQ = new MyQueue<>();
        stack = new MyStack<>();
        fileName = args[0].replaceAll("\"", "");
        parseXMLFile();
    }

    /**
     * check if a tag is self-closing
     * @param tag tag to be checked
     * @return true if the tag is self-closing
     */
    private boolean isSelfClosingTag(String tag) {
        return tag.charAt(tag.length() - 2) == '/';
    }

    /**
     * push a start tag onto the stack
     * @param tag to be pushed to the stack
     */
    private void pushStartTag(String tag) {
        String tempTag = tag.replaceAll("[<>]", "");
        try {
            stack.push(tempTag.substring(0, tag.indexOf(' ')).trim());
        } catch (StringIndexOutOfBoundsException ex) {
            stack.push(tempTag.trim());
        }
    }

    /**
     * check if an end tag has a corresponding start tag in the stack
     * if there is no matching tag, the param tag will be added to the errorQ or extraQ
     * @param tag end tag to be checked
     */
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

    /**
     * remove any tags remaining in stack and add them to errorQ
     */
    private void cleanStack() {
        while (stack.hasNext()) {
            errorQ.enqueue(stack.pop().trim());
        }
    }

    /**
     * if one queue has items in it, but not both queues, dequeue all items in that queue
     */
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

    /**
     * clean both queues if they have items in them
     * check if the queues have matching tags in them
     * clear queue with any remaining elements at the end of comparison
     */
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
     * @param tag tag to be checked
     * @return true if second character is /
     */
    private boolean isClosingTag(String tag) {
        return tag.charAt(1) == '/';
    }

    /**
     * check if a given tag/line is in proper XML format
     * @param tag tag to checked for proper format
     * @return true if the tag is in proper format
     */
    private boolean isProperFormat(String tag) {
        return tag.charAt(0) == '<' && tag.charAt(tag.length() - 1) == '>';
    }

    /**
     * parse the XML file supplied to the program.
     * System will exit (error code 1) if an improper file path is supplied.
     */
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
