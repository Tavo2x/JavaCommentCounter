public class CommentCounter {
//- Variables

    private int codeNum = 0;
    private int singleLineNum = 0;
    private int multieCommentNum = 0;
    private int javaDocNum = 0; 

    private StringBuilder code; // 
    private StringBuilder comment; // 
    private StringBuilder javaDoc; // 
    private StringBuilder original; // 

    private State currentState;
//- Constructor
    public CommentCounter(){
        code = new StringBuilder();
        comment = new StringBuilder();
        javaDoc = new StringBuilder();
        original = new StringBuilder();
        currentState = new CodeState();
    }
//- Updates current state
    public void setState(State st){
        currentState = st;
    }
//- Handles char by char
//- Possible char's it might come across
    public void processChar(char c){
        switch (c) {
            case '/':
                currentState.forwardSlash(this);
                break;
            case '\\':
                currentState.backwardSlash(this);
                break;
            case '*':
                currentState.star(this);
                break;
            case '\n':
                currentState.newLine(this);
                break;                
            case '\"':
                currentState.doubleQuote(this);
                break;
            case '\'':
                currentState.singleQuote(this);
                break;
            default:
                currentState.other(this, c);
                break;
        }
    }
//-
    public void recordCode(char c){
        codeNum = codeNum + 1;
        code.append(c); // The code text will keep the code char
        comment.append('.'); // When viewing the comment tab will show a '.' instead of the actual char 
        javaDoc.append('.'); // When viewing the javaDoc tab will show a '.' instead of the actual char
        original.append(c); // Keeps the original
    }
//-
    public void recordSingleLineComment(char c){
        singleLineNum = singleLineNum + 1;
        code.append('.'); // When vierwing the code tab it'll show as '.'
        comment.append(c); // When viewing tab we will see the comment
        javaDoc.append('.'); // When viewing tab it'll read as '.'
        original.append(c); // Keeps the original
    }
//-
    public void recordMultiLineComment(char c){
        multieCommentNum = multieCommentNum + 1;
        code.append('.'); // Viewed as '.' instead of text
        comment.append(c); // Viewed as the char's that make up the comment
        javaDoc.append('.'); // Viewed as '.' instead of text
        original.append(c); // Keeps the original
    }
//- 
    public void recordJavaDocComment(char c){
        javaDocNum = javaDocNum + 1;
        code.append('.'); // We wanna see '.' instead of the actual character
        comment.append('.'); // We wanna see '.' instead of the actual char due to it being a diffrent kind of comment when viewing in tabs
        javaDoc.append(c); // We wanna see the char that makes up that javaDoc commemnt
        original.append(c); // Keeps the original
    }
//-
    public  void recordNewLine(){
        code.append('\n'); // Keeps the new line in the code tab
        comment.append('\n'); // When having a new line in a comment it should reflect that
        javaDoc.append('\n'); // Keeping the new line in the javaDoc tab
        original.append('\n'); // Keeps the original
    }
//- Returns the code number
    public int getCodeNum(){
        return codeNum;
    }
//- Returns the singleline number
    public int getSingleLineNum(){
        return singleLineNum;
    }
//- Returns the multiLineComment number
    public int getMultiLineCommentNum(){
        return multieCommentNum;
    }
//- Returns the javaDoc number
    public int getJavaDocNum(){
        return javaDocNum;
    }
//- Returns the code string for the GUI 
    public String getCodeText(){
        return code.toString();
    }
//- Returns the comment string for the GUI
    public String getCommentText(){
        return comment.toString();
    }
//- Returns the javaDoc string for the GUI
    public String getJavaDocText(){
        return javaDoc.toString();
    }
//- Returns the original text for the GUI
    public String getOriginalText(){
        return original.toString();
    }

}
