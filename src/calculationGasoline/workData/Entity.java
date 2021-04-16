package calculationGasoline.workData;

import java.io.Serial;
import java.io.Serializable;

public class Entity implements Serializable {


    @Serial
    private static final long serialVersionUID = 9092913823349884190L;

    private Long id;


  public Entity(){

  }

    public Long getId() {
        return id;
    }
}
