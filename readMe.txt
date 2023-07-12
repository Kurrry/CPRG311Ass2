Authors: Avery Johnson-Dhillon, John Holloway, Eric Gagne, Joshua Lokhorst

How to install and use:
Using command line type "java -jar Parser.jar <some file location>" with everything between the <> being placeholders.
please do not include <> in arguments.

Completeness: 100%

Deficiencies or missing functionality:
This parser is extremely primitive and it only parses whether tags are valid, not attributes or xml rules.
This parser is optimal if one line contains a single tag, either opening or closing/self-closing.
This parser struggles if the above formatting rules are not maintained.
This parser will not display the line on which the error occurs, merely the name of the tag that is causing the error.
Similarly, any extra tags will not have line numbers displayed.
