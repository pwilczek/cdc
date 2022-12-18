package name.wilu.zch.cdc.debezium;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.connect.data.Struct;

abstract class EventHandler {
    //
    abstract boolean canTake(Struct event);
    abstract void take(Struct event);

    protected String value(Struct after, String name) {
        return after.get(name).toString();
    }

    protected boolean isEvent(Struct state, String name) {
        return state.schema().fields().stream().anyMatch(it -> StringUtils.equals(it.name(), name));
    }
}