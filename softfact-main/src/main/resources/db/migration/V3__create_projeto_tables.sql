create table if not exists tb_softfact_projeto (
    id            bigserial primary key,
    nome          varchar(200) not null,
    descricao     text,
    semestre      varchar(20)  not null,
    tipo          varchar(20)  not null,
    nome_empresa  varchar(200),
    status        varchar(20)  not null,
    constraint ck_projeto_tipo   check (tipo in ('EMPRESA_PARCEIRA', 'AUTORAL')),
    constraint ck_projeto_status check (status in ('PLANEJADO', 'EM_ANDAMENTO', 'CONCLUIDO', 'CANCELADO'))
);

create index if not exists idx_projeto_nome     on tb_softfact_projeto (nome);
create index if not exists idx_projeto_semestre on tb_softfact_projeto (semestre);
create index if not exists idx_projeto_status   on tb_softfact_projeto (status);

create table if not exists tb_softfact_projeto_stack (
    projeto_id bigint not null,
    stack_id   bigint not null,
    primary key (projeto_id, stack_id),
    constraint fk_projeto_stack__projeto
        foreign key (projeto_id) references tb_softfact_projeto(id)
            on delete cascade,
    constraint fk_projeto_stack__stack
        foreign key (stack_id) references tb_softfact_stack(id)
            on delete cascade
);

create table if not exists tb_softfact_projeto_aluno (
    projeto_id bigint not null,
    aluno_id   bigint not null,
    primary key (projeto_id, aluno_id),
    constraint fk_projeto_aluno__projeto
        foreign key (projeto_id) references tb_softfact_projeto(id)
            on delete cascade,
    constraint fk_projeto_aluno__aluno
        foreign key (aluno_id) references tb_softfact_aluno(id)
            on delete cascade
);
