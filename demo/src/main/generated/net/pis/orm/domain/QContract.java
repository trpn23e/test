package net.pis.orm.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.Expression;


/**
 * QContract is a Querydsl query type for Contract
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContract extends EntityPathBase<Contract> {

    private static final long serialVersionUID = 455482483L;

    public static ConstructorExpression<Contract> create(Expression<Integer> id, Expression<String> contractStatus) {
        return Projections.constructor(Contract.class, new Class<?>[]{int.class, String.class}, id, contractStatus);
    }

    public static ConstructorExpression<Contract> create(Expression<String> contractFileHash) {
        return Projections.constructor(Contract.class, new Class<?>[]{String.class}, contractFileHash);
    }

    public static ConstructorExpression<Contract> create(Expression<String> transactionId, Expression<String> senderName, Expression<String> senderBrokerName, Expression<String> receiverName, Expression<String> receiverBrokerName, Expression<String> contractStatus, Expression<java.time.LocalDateTime> updatedDatetime) {
        return Projections.constructor(Contract.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, transactionId, senderName, senderBrokerName, receiverName, receiverBrokerName, contractStatus, updatedDatetime);
    }

    public static final QContract contract = new QContract("contract");

    public final StringPath addtionalFileGroupId = createString("addtionalFileGroupId");

    public final StringPath contractFileHash = createString("contractFileHash");

    public final StringPath contractFileLink = createString("contractFileLink");

    public final StringPath contractFileName = createString("contractFileName");

    public final StringPath contractNumber = createString("contractNumber");

    public final StringPath contractStatus = createString("contractStatus");

    public final DateTimePath<java.time.LocalDateTime> creationDatetime = createDateTime("creationDatetime", java.time.LocalDateTime.class);

    public final StringPath creationUserId = createString("creationUserId");

    public final StringPath finalContractFileHash = createString("finalContractFileHash");

    public final StringPath finalContractFileLink = createString("finalContractFileLink");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath ownerAddress = createString("ownerAddress");

    public final StringPath ownerName = createString("ownerName");

    public final StringPath receiverAddress = createString("receiverAddress");

    public final StringPath receiverBrokerAddress = createString("receiverBrokerAddress");

    public final StringPath receiverBrokerName = createString("receiverBrokerName");

    public final StringPath receiverName = createString("receiverName");

    public final DateTimePath<java.time.LocalDateTime> receptionDatetime = createDateTime("receptionDatetime", java.time.LocalDateTime.class);

    public final StringPath senderAddress = createString("senderAddress");

    public final StringPath senderBrokerAddress = createString("senderBrokerAddress");

    public final StringPath senderBrokerName = createString("senderBrokerName");

    public final StringPath senderName = createString("senderName");

    public final DateTimePath<java.time.LocalDateTime> sentDatetime = createDateTime("sentDatetime", java.time.LocalDateTime.class);

    public final StringPath smartContractAddress = createString("smartContractAddress");

    public final StringPath transactionId = createString("transactionId");

    public final StringPath transferStatus = createString("transferStatus");

    public final DateTimePath<java.time.LocalDateTime> updatedDatetime = createDateTime("updatedDatetime", java.time.LocalDateTime.class);

    public final StringPath updatedUserId = createString("updatedUserId");

    public QContract(String variable) {
        super(Contract.class, forVariable(variable));
    }

    public QContract(Path<? extends Contract> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContract(PathMetadata metadata) {
        super(Contract.class, metadata);
    }

}

