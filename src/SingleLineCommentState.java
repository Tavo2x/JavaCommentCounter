public class SingleLineCommentState implements State{
//- Handles char's when in a single line comment

//- The char '/' is recorded as a single line comment
    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordSingleLineComment('/');
    }

//- The char '\' is recorded as a single line comment
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordSingleLineComment('\\');
    }

//- The char '*' is recorded as a single line comment
    @Override
    public void star(CommentCounter c) {
        c.recordSingleLineComment('*');
    }

//- Records new line w/ help of the newLine helper
//- Swithces to Code state since the single line comment has ended
    @Override
    public void newLine(CommentCounter c) {
        c.recordNewLine();
        c.setState(new CodeState());
    }

//- The char '"' is recorded as a single line comment
    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordSingleLineComment('"');
    }

//- The char ''' is recorded as a single line comment
    @Override
    public void singleQuote(CommentCounter c) {
        c.recordSingleLineComment('\'');
    }
   
//- Anything else is recorded as a single line comment
    @Override
    public void other(CommentCounter c, char symbol){
        c.recordSingleLineComment(symbol);
    }

}
