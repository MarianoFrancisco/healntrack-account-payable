create table account_payable (
    id uuid primary key,
    hospitalization_id uuid unique not null,
    total_fee numeric(10,2) not null,
    status varchar(7) check (status in ('PENDING', 'CLOSED')) not null default 'PENDING',
    closing_date date,
    created_at timestamp not null,
    updated_at timestamp not null
);

create table service_type (
    id uuid primary key default gen_random_uuid(),
    name varchar(20) unique not null
);

create table account_payable_item (
    id uuid primary key,
    account_payable_id uuid not null,
    service_type_id uuid not null,
    reference_id uuid unique not null,
    fee numeric(10,2) not null,
    service_date date not null,
    created_at timestamp not null,
    updated_at timestamp not null,
    foreign key(account_payable_id) references account_payable(id),
    foreign key(service_type_id) references service_type(id)
);