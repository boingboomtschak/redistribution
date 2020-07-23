# Redistribution
[![Version image](https://img.shields.io/badge/Version-0.3.0-green)](https://github.com/d-mckee/redistribution/commits/master) [![Current size of plugin JAR](https://img.shields.io/github/size/d-mckee/redistribution/target/RedistributionPlugin-0.3.0.jar)](https://github.com/d-mckee/redistribution/tree/master/target)

A plugin for Spigot/Paperspigot Java Edition Minecraft servers to redistribute the resources of banned players to the community. Currently in alpha stages to create basic versions of planned plugin systems, and get to a working state. 

## Plugin Description

[Placeholder]

## Current to-do list:

- Modify pool list command to indicate whether a pool is currently loaded or not
- Add command to sort pool, while pool is currently loaded
- Extend pool sorting to unloaded pools
- Add command to manually save pools, if need be
- Input validation for pool names to fit standard filesystem compatibility
- Implement basic class for finalized pools
- Implement command to finalize pools
- Add basic claiming system
- Add claiming strategies to pool, and store in pool serialization
- Track claiming of each pool by a player with stored data inside each pool
- Permissions for plugin commands
- TBD

## Version Changelog

- 0.3.0: Advanced deserialization of items with NBT data, pools can now be loaded/saved as needed (may be background tasks in future versions)
- 0.2.1: Advanced serialization of items with NBT data, format is temporary/may be cleaned up later.
- 0.2.0: Basic serialization implementation without NBT data, reworking of repo readme.
- 0.1.1: Set up help pages, both for main "/redis" command, as well as "/redis pool" subcommand
- 0.1.0: Implementation of RedisPoolManager, allowing for multiple pools to be in memory simultaneously, as well as managing functions for specific pools.
- 0.0.3: Fixed inventory click cancellation, next/prev page buttons, reorganization of "/redis" command.
- 0.0.2: Implementation of inventory arrays and inventory click event cancellation.
- 0.0.1: Initial setup, basic custom GUI code, "/redis" command.
