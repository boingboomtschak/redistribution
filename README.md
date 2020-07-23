# Redistribution
[![Version image](https://img.shields.io/badge/Version-0.3.1-green)](https://github.com/d-mckee/redistribution/commits/master) [![Current size of plugin JAR](https://img.shields.io/github/size/d-mckee/redistribution/target/RedistributionPlugin-0.3.1.jar)](https://github.com/d-mckee/redistribution/tree/master/target)

A plugin for Spigot/Paperspigot Java Edition Minecraft servers to redistribute the resources of banned players to the community. Currently in alpha stages to create basic versions of planned plugin systems, and get to a working state. 

## Plugin Description

Redistribution is a Minecraft Spigot server plugin geared towards distributing (or, most commonly, redistributing) items from various sources to players by way of item pools, referred to as pools in this documentation. 

The intended purpose of this plugin is for the source of those items to be from the items of banned players, so that their work in the server is still helpful to other players. However, the plugin can also be used more generally to distribute items to players as part of a server giveaway, resources that a player can claim, etc. 

**Plugin Objects:**

| Object | Description | Help Command | Implementation Status |
|--------|-------------|--------------|-----------------------|
| Pool | A collection of items, accessible through a custom GUI and paginated for ease of use. Saved under `/plugins/Redistribution/pools`. | `/redis pool help` | In Progress |
| Finalized Pool | A pool after being finalized through `/redis pool finalize <pool>` (not implemented). Read-only, can be opened through GUI, but cannot be edited after being finalized. Items will be removed as players collect their claims. Will be saved under `/plugins/Redistribution/finalized-pools`. | `/redis final help` | Not implemented |
| Claim | A portion of items from a finalized pool, given to each player with specified permission for that pool (not implemented). | `/redis claim help` | Not implemented |
| Claim Dictionary | A collection of the claim status for a specific pool. Keeps track of all players who have claimed from the pool. | `/redis claims help` | Not implemented |

**Plugin Permissions:**

[TODO]

**Plugin Configurable Options:**

| Option | Description | Values | Default Value | Implementation Status |
|--------|-------------|--------|---------------|-----------------------|
| `purge_final_claimed` | Determines whether `purge_final_percent`% claimed pools will be purged from the config folder | `<true/false>` | `false` | Not implemented |
| `purge_final_percent` | Determines percentage of items claimed in a finalized pool before it is purged from the config. | `<0.0 - 100.0>` | `85%` |



## Current to-do list:

- Add function to sort pools, called on serialization
- Add command to sort pool items, while pool is currently loaded
- Extend pool sorting to unloaded pools
- Input validation for pool names to fit standard filesystem compatibility
- Implement basic class for finalized pools
- Implement command to finalize pools
- Add basic claiming system
- Implement command to purge finalized pool from config and claim dictionary
- Implement command to reset claims for a specific user in a pool
- Implement resetting claims for all users in a pool
- Add claiming strategies to pool, and store in pool serialization
- Track claiming of each pool by a player with stored data inside each pool
- Permissions for plugin commands
- TBD

## Version Changelog

- 0.3.1: Listing of unloaded pools, alphabetic sorting of pools independent of loaded status
- 0.3.0: Advanced deserialization of items with NBT data, pools can now be loaded/saved as needed (may be background tasks in future versions)
- 0.2.1: Advanced serialization of items with NBT data, format is temporary/may be cleaned up later.
- 0.2.0: Basic serialization implementation without NBT data, reworking of repo readme.
- 0.1.1: Set up help pages, both for main "/redis" command, as well as "/redis pool" subcommand
- 0.1.0: Implementation of RedisPoolManager, allowing for multiple pools to be in memory simultaneously, as well as managing functions for specific pools.
- 0.0.3: Fixed inventory click cancellation, next/prev page buttons, reorganization of "/redis" command.
- 0.0.2: Implementation of inventory arrays and inventory click event cancellation.
- 0.0.1: Initial setup, basic custom GUI code, "/redis" command.
