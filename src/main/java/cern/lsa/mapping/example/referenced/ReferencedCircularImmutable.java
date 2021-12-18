package cern.lsa.mapping.example.referenced;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Value.Immutable(copy = false)
@Value.Modifiable
@Value.Style(depluralize = true, typeImmutable = "Default*", get = {"get*", "is*", "are*"}, jdkOnly = true)
@JsonIdentityInfo(property = "name", generator = ObjectIdGenerators.PropertyGenerator.class)
@JsonSerialize(as = DefaultReferencedCircularImmutable.class)
@JsonDeserialize(as = DefaultReferencedCircularImmutable.class)
public abstract class ReferencedCircularImmutable {

    @Nullable
    @JsonIgnore
    abstract AtomicReference<ReferencedCircularImmutable> getParentInt();
    @JsonIgnore
    abstract List<AtomicReference<ReferencedCircularImmutable>> getChildrenInt();
    @JsonIgnore
    abstract String getUniqueId();
    public abstract String getName();
    public abstract String getTitle();
    public abstract String getMessage();

    @Nullable
    public final ReferencedCircularImmutable getParent() {
        return getParentInt() != null ? getParentInt().get() : null;
    }

    public final List<ReferencedCircularImmutable> getChildren() {
        return getChildrenInt().stream().map(AtomicReference::get).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + getTitle() + " " + getName() + " says " + getMessage()
                + ", parent=" + (getParent() == null ? "null" : getParent().getName())
                + ", children=[" + getChildren().stream().map(ReferencedCircularImmutable::getName).collect(Collectors.joining(", ")) + "]"
                + "}";
    }

    @Value.Check
    void propagateImmutability() {
        AtomicReference<ReferencedCircularImmutable> parent = getParentInt();
        getChildrenInt().forEach(child -> Objects.requireNonNull(child.get().getParentInt()).set(this));
        if (parent != null && parent.get() instanceof ModifiableReferencedCircularImmutable) {
            updateParentsChild();
            replaceReference.accept(getParentInt());
        }
        getChildrenInt().forEach(replaceReference);
    }

    private static final Consumer<AtomicReference<ReferencedCircularImmutable>> replaceReference = reference -> {
        if (reference.get() instanceof ModifiableReferencedCircularImmutable) {
            reference.set(((ModifiableReferencedCircularImmutable) reference.get()).toImmutable());
        }
    };

    private void updateParentsChild() {
        Objects.requireNonNull(getParentInt())
                .get()
                .getChildrenInt()
                .stream()
                .filter(child -> child.get().getUniqueId().equals(getUniqueId()) && child.get() instanceof ModifiableReferencedCircularImmutable)
                .forEach(child -> child.set(this));
    }
}
