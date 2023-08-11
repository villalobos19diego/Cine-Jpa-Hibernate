        package org.villalobos19.repository;

        import org.villalobos19.entity.Actor;

        import java.util.List;

        public interface BusquedaActorRepository {
            List<Actor> actorListDeadPorNacion(String nacion);
            List<Object[]> actorMayorParticipacion();


        }
