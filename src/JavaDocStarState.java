public class JavaDocStarState implements State{
//- Handles char's after a '*' inside a JavaDoc comment

//- Records both ending char's as JavaDocComment
//- Swithces to codeState because the comment has eneded
    @Override
    public void forwardSlash(CommentCounter c) {
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('/');
        c.setState(new CodeState());
    }

//- Records both char's as JavaDocComment
//- Switches to JavaDocState, cuz the comment hasn't ended
    @Override
    public void backwardSlash(CommentCounter c) {
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('\\');
        c.setState(new JavaDocState());
    }

//- Recrods '*' as part of the JavaDocComment
    @Override
    public void star(CommentCounter c) {
        c.recordJavaDocComment('*');
    }

//- Records '*' as part of the JavaDocComment
//- record new line with the newLine helper
//- Comment hasn't ended therefore swwitch to JavaDocState
    @Override
    public void newLine(CommentCounter c) {
        c.recordJavaDocComment('*');
        c.recordNewLine();
        c.setState(new JavaDocState());
    }

//- Record both char's (*,") as part of JavaDocComment
//- Comment hasn't ended switch JavaDocState
    @Override
    public void doubleQuote(CommentCounter c) {
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('"');
        c.setState(new JavaDocState());
    }
//- Record both char's (*,'') as part of JavaDocComment
//- Comment hasn't ended switch JavaDocState
    @Override
    public void singleQuote(CommentCounter c) {
        c.recordJavaDocComment('*');
        c.recordJavaDocComment('\'');
        c.setState(new JavaDocState());
    }
//- Record char's as JavaDocComment
//- Switch to JavaDocState the comment has not ended
    @Override
    public void other(CommentCounter c, char symbol) {
        c.recordJavaDocComment('*');
        c.recordJavaDocComment(symbol);
        c.setState(new JavaDocState());
    }

}
