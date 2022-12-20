<!-- TOC -->
* [Database design](#database-design)
    * [Student](#student)
    * [Teacher](#teacher)
    * [Course](#course)
    * [Cookie](#cookie)
    * [Job](#job)
<!-- TOC -->

# Database design
## Student

| property | type        | constrain   | description |
|----------|-------------|-------------|-------------|
| Sid      | varchar(20) | primary key |             |
| Sname    | varchar(20) |             |             |

## Teacher

| property | type        | constrain   | description |
|----------|-------------|-------------|-------------|
| Tid      | varchar(20) | primary key |             |
| Tname    | varchar(20) |             |             |

## Course

| property | type        | constrain   | description |
|----------|-------------|-------------|-------------|
| Cid      | varchar(20) | primary key |             |
| Cname    | varchar(20) |             |             |

## Cookie

| property  | type         | constrain | description |
|-----------|--------------|-----------|-------------|
| CookieId  | varchar(100) | primary   |             |
| Pid       | varchar(20)  | pid       |             |
| live_time | int          |           |             |

## Job

| property | type        | constrain                         | description |
|----------|-------------|-----------------------------------|-------------|
| Jid      | varchar(20) | primary key                       |             |
| Cid      | varchar(20) | foreign key reference Course(Cid) |             |
| Jcontent | text        |                                   |             |