How to install and use:
Using command line type "java -jar Parser.jar <some file location>" with everything between the <> being placeholders.
please do not include <> in arguments.

Completeness: 100%

Deficiencies or missing functionality:
This parser is extremely primitive and it only parses whether tags are valid, not attributes or xml rules.
This parser struggles with complex syntax errors with multiple tags on a single line, such as "<a><b>/a></b>".
This parser is optimal if one line contains a single tag, either opening or closing/self-closing. 