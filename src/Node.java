import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NonNull;
        import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class Node {
    //Node cannot exist without following details
    @NonNull
    private String name;
    @NonNull
    private Folder parentFolder;
    @NonNull
    private String doe;

}
