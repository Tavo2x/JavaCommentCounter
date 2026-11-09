public class StarState implements State {
//- Handles char's after seeing a '/' followed by a '*'

//- Records '/*/' as part of the MultiLineComment
//- Switches to MultiLineCommentState to resume reading the comment
    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordMultiLineComment('/');
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('/');
        c.setState(new MultiLineCommentState());
    }

//- Records '/*\' as part of the MultiLineComment
//- Switches to MultiLineCommentState to continue reading the comment
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordMultiLineComment('/');
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('\\');
        c.setState(new MultiLineCommentState());
    }

//- Switches to JavaDocStartState to verify if the comment is a JavaDocComment
    @Override
    public void star(CommentCounter c) {
        c.setState(new JavaDocStartState());
    }

//- Records '/*' as part of the MultiLineComment
//- Switches to MultiLineCommentState to continue reading the comment
    @Override
    public void newLine(CommentCounter c) {
        c.recordMultiLineComment('/');
        c.recordMultiLineComment('*');
        c.recordNewLine();
        c.setState(new MultiLineCommentState());
    }

//- Records '/*' and '"' as part of the MultiLineComment 
//- Switches to MultiLineCommentState to continue reading the comment

    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordMultiLineComment('/');
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('"');
        c.setState(new MultiLineCommentState());
    }

//- Records '/*' and ''' as part of the MultiLineComment 
//- Switches to MultiLineCommentState to continue reading the comment

    @Override
    public void singleQuote(CommentCounter c) {
        c.recordMultiLineComment('/');
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('\'');
        c.setState(new MultiLineCommentState());
    }

//- Records all three char's as part of the MultiLineComment
//- Switches to MultiLineCommentState to continue reading the comment
    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordMultiLineComment('/');
        c.recordMultiLineComment('*');
        c.recordMultiLineComment(symbol);
        c.setState(new MultiLineCommentState());
    }

}
