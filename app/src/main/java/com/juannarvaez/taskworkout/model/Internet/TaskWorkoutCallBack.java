package com.juannarvaez.taskworkout.model.Internet;

public interface TaskWorkoutCallBack<T>{
    void tareaCorrecta (T respuesta);
    void tareaError (Exception exception);
}
