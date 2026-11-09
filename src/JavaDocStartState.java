public class JavaDocStartState implements State{
//- Handles char's after '/**' to determine if it a JavaDoc comment

//- Record '/**/' as MultiLineComment
//- Swithc to CodeState comment has not ended
    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordMultiLineComment('/');
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('*');
        c.recordMultiLineComment('/');
        c.setState(new CodeState());
    }

//- Record '/**\' as JavaDocComment
//- Swithc to CodeState comment has not ended
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordJavaDocComment('/');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('\\');
        c.setState(new JavaDocState());
    }

//- Record '/**' as JavaDocComment
//- Switch to JavaDocStarState, verifiy if it ends with '*/'
    @Override
    public void star(CommentCounter c) {
        c.recordJavaDocComment('/');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('*');
        c.setState(new JavaDocStarState());
    }

//- Record '/**' as JavaDocComment
//- Record new line with the newLine helper
//- Switch to JavaDocState comment has not ended
    @Override
    public void newLine(CommentCounter c) {
        c.recordJavaDocComment('/');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('*');
        c.recordNewLine();
        c.setState(new JavaDocState());
    }

//- Record '/**' and '"' as JavaDocComment
//- Switch to JavaDocState comment has not ended
    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordJavaDocComment('/');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('"');
        c.setState(new JavaDocState());

    }

//- Record '/**' and ''' as JavaDocComment
//- Switch to JavaDocState comment has not ended
    @Override
    public void singleQuote(CommentCounter c) {
        c.recordJavaDocComment('/');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('\'');
        c.setState(new JavaDocState());
    }

//- Record '/**' and the current char as part of JavaDocComment
//- Switch to JavaDocState comment has not ended
    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordJavaDocComment('/');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('*');
        c.recordJavaDocComment(symbol);
        c.setState(new JavaDocState());
    }
    
}
