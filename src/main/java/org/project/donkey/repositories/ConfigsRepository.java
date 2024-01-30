package org.project.donkey.repositories;

import org.project.donkey.entities.Configs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigsRepository extends JpaRepository<Configs, String> {
}